/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data

import com.emilpana.directoryapp.data.local.database.Database
import com.emilpana.directoryapp.data.local.mapper.fromLocalPerson
import com.emilpana.directoryapp.data.local.mapper.toLocalPerson
import com.emilpana.directoryapp.data.remote.ApiService
import com.emilpana.directoryapp.data.remote.mapper.fromRemotePerson
import com.emilpana.directoryapp.domain.entity.model.Person
import com.emilpana.directoryapp.domain.repository.PeopleRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(val database: Database, val apiService: ApiService) :
    PeopleRepository {
    override fun getRemotePeople(): Single<List<Person>> {
        return apiService.getPeople().map { it.map { person -> person.fromRemotePerson() } }
    }

    override fun getLocalPeople(): Single<List<Person>> =
        Single.create { emitter ->
            emitter.onSuccess(
                database.personDao().getAllPersons().map { it.fromLocalPerson() })
        }

    override fun replaceLocalPeople(persons: List<Person>) {
        database.personDao().deleteAllPersons()
        database.personDao().insertAll(persons.map { it.toLocalPerson() })
    }
}
