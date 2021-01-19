/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data

import com.emilpana.directoryapp.data.local.Database
import com.emilpana.directoryapp.data.remote.ApiService
import com.emilpana.directoryapp.domain.entity.model.Room
import com.emilpana.directoryapp.domain.repository.RoomRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(val database: Database, val apiService: ApiService) :
    RoomRepository {
    override fun getAllRooms(): Single<List<Room>> {
        return apiService.getRooms()
    }
}
