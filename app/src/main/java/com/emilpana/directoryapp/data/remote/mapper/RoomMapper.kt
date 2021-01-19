/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data.remote.mapper

import com.emilpana.directoryapp.data.remote.entity.RemoteRoom
import com.emilpana.directoryapp.domain.entity.model.Room

fun RemoteRoom.fromRemoteRoom() = Room(
    createdAt, id, isOccupied, maxOccupancy, name
)

fun Room.toRemoteRoom() = RemoteRoom(
    createdAt, id, isOccupied, maxOccupancy, name
)
