/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data.remote.entity

data class RemotePerson(
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
