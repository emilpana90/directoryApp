/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.domain.repository

import com.emilpana.directoryapp.domain.entity.model.Person
import io.reactivex.rxjava3.core.Single

interface PeopleRepository {
    fun getAllPeople(): Single<List<Person>>
}
