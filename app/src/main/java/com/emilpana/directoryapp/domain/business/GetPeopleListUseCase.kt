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
import javax.inject.Inject

class GetPeopleListUseCase @Inject constructor(val peopleRepository: PeopleRepository) {
    fun execute(): Single<PersonsListContainer> {
        return peopleRepository.getAllPeople().map { PersonsListContainer(it) }.doOnSuccess {
            Log.d(PeopleRepositoryImpl::class.simpleName, "personsList loaded")
        }.onErrorResumeNext {
            Log.d(PeopleRepositoryImpl::class.simpleName, "personsList load failed")
            Single.just(
                PersonsListContainer(null, it)
            )
        }
    }
}
