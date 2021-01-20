package com.emilpana.directoryapp.domain.business

import com.emilpana.directoryapp.domain.entity.model.PersonContainer
import com.emilpana.directoryapp.domain.repository.PeopleRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(val peopleRepository: PeopleRepository) {
    fun execute(personId: String): Single<PersonContainer> {
        return peopleRepository.getLocalPerson(personId)
            .map { PersonContainer(it) }
            .onErrorResumeNext { error ->
                when (error) {
                    else -> Single.just(PersonContainer(null, error))
                }
            }
    }
}
