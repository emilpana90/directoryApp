package com.emilpana.directoryapp.domain.business

import com.emilpana.directoryapp.domain.entity.model.PersonsListContainer
import com.emilpana.directoryapp.domain.repository.PeopleRepository
import io.reactivex.rxjava3.core.Single
import java.io.IOException
import javax.inject.Inject

class GetPeopleListUseCase @Inject constructor(val peopleRepository: PeopleRepository) {
    fun execute(): Single<PersonsListContainer> {
        return peopleRepository.getRemotePeople()
            .map { PersonsListContainer(it, isDataOld = false) }.flatMap { personsListContainer ->
                // Save to db
                personsListContainer.peopleList?.let { personsList ->
                    peopleRepository.replaceLocalPeople(personsList)
                        .toSingleDefault(personsListContainer)
                } ?: // Return empty list, if people list is empty from API
                Single.just(
                    PersonsListContainer(
                        peopleList = listOf(),
                        isDataOld = false
                    )
                )
            } // In case the API fails, try to handle depending on error type
            .onErrorResumeNext { error ->
                when (error) {
                    // If no internet, get from local database
                    is IOException -> {
                        // Get from db
                        peopleRepository.getLocalPeople()
                            .map { peopleList ->
                                if (peopleList.isEmpty()) {
                                    PersonsListContainer(
                                        null,
                                        // Rethrow error if cached list is empty
                                        error,
                                        isDataOld = false
                                    )
                                } else {
                                    PersonsListContainer(
                                        peopleList,
                                        null,
                                        isDataOld = true
                                    )
                                }
                            }
                            // Trigger a container for error, else the error will not be catched by
                            // livedata reactive streams
                            .onErrorResumeNext {
                                Single.just(
                                    PersonsListContainer(
                                        null,
                                        error,
                                        false
                                    )
                                )
                            }
                    }
                    // Trigger a container for error, else the error will not be catched by livedata
                    // reactive streams
                    else -> Single.just(PersonsListContainer(null, error, false))
                }
            }
    }
}
