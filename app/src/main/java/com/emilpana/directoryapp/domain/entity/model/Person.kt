/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.domain.entity.model

data class PersonsListContainer(
    val peopleList: List<Person>?,
    val error: Throwable? = null,
    val isDataOld: Boolean
)

data class PersonContainer(
    val person: Person?,
    val error: Throwable? = null
)

data class Person(
    val avatar: String,
    val createdAt: String,
    val email: String,
    val favouriteColor: String,
    val firstName: String,
    val id: String,
    val jobTitle: String,
    val lastName: String,
    val latitude: Double,
    val longitude: Double,
    val phone: String
)
