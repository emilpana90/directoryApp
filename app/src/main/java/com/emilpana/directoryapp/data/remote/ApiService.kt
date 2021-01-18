/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data.remote

import com.emilpana.directoryapp.domain.entity.Person
import com.emilpana.directoryapp.domain.entity.Room
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("people")
    fun getPeople(): Single<List<Person>>

    @GET("rooms")
    fun getRooms(): Single<List<Room>>
}
