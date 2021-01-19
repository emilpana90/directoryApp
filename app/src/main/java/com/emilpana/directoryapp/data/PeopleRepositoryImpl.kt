/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data

import com.emilpana.directoryapp.data.local.Database
import com.emilpana.directoryapp.data.remote.ApiService
import com.emilpana.directoryapp.domain.entity.model.Person
import com.emilpana.directoryapp.domain.repository.PeopleRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(val database: Database, val apiService: ApiService) :
    PeopleRepository {
    override fun getAllPeople(): Single<List<Person>> {
        return apiService.getPeople()
    }
}
