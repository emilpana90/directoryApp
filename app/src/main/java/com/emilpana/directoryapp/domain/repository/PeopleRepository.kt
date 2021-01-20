package com.emilpana.directoryapp.domain.repository

import com.emilpana.directoryapp.domain.entity.model.Person
import io.reactivex.rxjava3.core.Single

interface PeopleRepository {
    fun getRemotePeople(): Single<List<Person>>

    fun getLocalPeople(): Single<List<Person>>

    fun replaceLocalPeople(persons: List<Person>)

    fun getLocalPerson(personId: String): Single<Person>
}
