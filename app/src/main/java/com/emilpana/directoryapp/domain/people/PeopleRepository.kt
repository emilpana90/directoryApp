/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.domain.people

import com.emilpana.directoryapp.domain.entity.model.PersonsListContainer
import io.reactivex.rxjava3.core.Single

interface PeopleRepository {
    fun getAllPeople(): Single<PersonsListContainer>
}
