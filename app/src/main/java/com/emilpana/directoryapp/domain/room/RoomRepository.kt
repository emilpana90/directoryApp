/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.domain.room

import com.emilpana.directoryapp.domain.entity.model.Room
import io.reactivex.rxjava3.core.Single

interface RoomRepository {
    fun getAllRooms(): Single<List<Room>>
}
