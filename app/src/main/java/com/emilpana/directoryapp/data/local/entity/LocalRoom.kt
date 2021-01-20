package com.emilpana.directoryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class LocalRoom(
    val createdAt: String,
    @PrimaryKey val id: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int,
    val name: String
)
