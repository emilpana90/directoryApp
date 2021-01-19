/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.domain.business

import android.util.Log
import com.emilpana.directoryapp.data.PeopleRepositoryImpl
import com.emilpana.directoryapp.domain.entity.model.PersonsListContainer
import com.emilpana.directoryapp.domain.repository.PeopleRepository
import io.reactivex.rxjava3.core.Single
import java.io.IOException
import javax.inject.Inject

class GetPeopleListUseCase @Inject constructor(val peopleRepository: PeopleRepository) {
    fun execute(): Single<PersonsListContainer> {
        return peopleRepository.getRemotePeople().map { PersonsListContainer(it) }.doOnSuccess {
            // Save to db
            it.peopleList?.let {
                peopleRepository.replaceLocalPeople(it)
            }

            Log.d(PeopleRepositoryImpl::class.simpleName, "personsList loaded")
        }.onErrorResumeNext { error ->
            Log.d(PeopleRepositoryImpl::class.simpleName, "personsList load failed")
            when (error) {
                // If no internet, get from local database
                is IOException -> {
                    // Get from db
                    peopleRepository.getLocalPeople()
                        .map { peopleList ->
                            PersonsListContainer(
                                peopleList,
                                // Rethrow error if cached list is empty
                                if (peopleList.isEmpty()) error else null
                            )
                        }
                        // Trigger a container for error, else the error will not be catched by
                        // livedata reactive streams
                        .onErrorResumeNext { Single.just(PersonsListContainer(null, error)) }
                }
                // Trigger a container for error, else the error will not be catched by livedata
                // reactive streams
                else -> Single.just(PersonsListContainer(null, error))
            }
        }
    }
}
