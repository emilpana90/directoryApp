package com.emilpana.directoryapp.data.remote.mapper

import com.emilpana.directoryapp.data.remote.entity.RemotePerson
import com.emilpana.directoryapp.domain.entity.model.Person

fun RemotePerson.fromRemotePerson() = Person(
    avatar, createdAt, email, favouriteColor, firstName, id, jobTitle, lastName, latitude, longitude, phone
)

fun Person.toRemotePerson() = RemotePerson(
    avatar, createdAt, email, favouriteColor, firstName, id, jobTitle, lastName, latitude, longitude, phone
)
