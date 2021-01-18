/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data

import android.util.Log
import com.emilpana.directoryapp.data.local.Database
import com.emilpana.directoryapp.data.remote.ApiService
import com.emilpana.directoryapp.domain.entity.model.PersonsListContainer
import com.emilpana.directoryapp.domain.people.PeopleRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(val database: Database, val apiService: ApiService) :
    PeopleRepository {
    override fun getAllPeople(): Single<PersonsListContainer> {
        return apiService.getPeople().map { PersonsListContainer(it) }.doOnSuccess {
            Log.d(PeopleRepositoryImpl::class.simpleName, "personsList loaded")
        }.onErrorResumeNext {
            Log.d(PeopleRepositoryImpl::class.simpleName, "personsList load failed")
            Single.just(
                PersonsListContainer(null, it)
            )
        }
    }
}
