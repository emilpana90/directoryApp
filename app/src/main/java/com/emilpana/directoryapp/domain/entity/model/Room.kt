/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.domain.entity.model

data class RoomListContainer(val roomList: List<Room>?, val error: Throwable? = null)
data class Room(
    val created_at: String,
    val id: String,
    val is_occupied: Boolean,
    val max_occupancy: Int,
    val name: String
)
