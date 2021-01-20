package com.emilpana.directoryapp.data.local.mapper

import com.emilpana.directoryapp.data.local.entity.LocalRoom
import com.emilpana.directoryapp.domain.entity.model.Room

fun LocalRoom.fromLocalRoom() = Room(
    createdAt, id, isOccupied, maxOccupancy, name
)

fun Room.toLocalRoom() = LocalRoom(
    createdAt, id, isOccupied, maxOccupancy, name
)
