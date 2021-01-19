/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.domain.entity.model

data class RoomListContainer(
    val roomList: List<Room>?,
    val error: Throwable? = null,
    val isDataOld: Boolean
)

data class Room(
    val createdAt: String,
    val id: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int,
    val name: String
)
