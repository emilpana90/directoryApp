/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.domain.repository

import com.emilpana.directoryapp.domain.entity.model.Room
import io.reactivex.rxjava3.core.Single

interface RoomRepository {
    fun getRemoteRooms(): Single<List<Room>>

    fun getLocalRooms(): Single<List<Room>>

    fun replaceLocalRooms(persons: List<Room>)
}
