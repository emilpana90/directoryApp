package com.emilpana.directoryapp.data.local.mapper

import com.emilpana.directoryapp.data.local.entity.LocalPerson
import com.emilpana.directoryapp.domain.entity.model.Person

fun LocalPerson.fromLocalPerson() = Person(
    avatar, createdAt, email, favouriteColor, firstName, id, jobTitle, lastName, latitude, longitude, phone
)

fun Person.toLocalPerson() = LocalPerson(
    avatar, createdAt, email, favouriteColor, firstName, id, jobTitle, lastName, latitude, longitude, phone
)
