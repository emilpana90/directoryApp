/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data.remote

import com.emilpana.directoryapp.data.remote.entity.RemotePerson
import com.emilpana.directoryapp.data.remote.entity.RemoteRoom
import com.emilpana.directoryapp.domain.entity.model.Person
import com.emilpana.directoryapp.domain.entity.model.Room
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("people")
    fun getPeople(): Single<List<RemotePerson>>

    @GET("rooms")
    fun getRooms(): Single<List<RemoteRoom>>
}
