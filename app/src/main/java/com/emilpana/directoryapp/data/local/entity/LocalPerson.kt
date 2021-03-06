package com.emilpana.directoryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class LocalPerson(
    val avatar: String,
    val createdAt: String,
    val email: String,
    val favouriteColor: String,
    val firstName: String,
    @PrimaryKey val id: String,
    val jobTitle: String,
    val lastName: String,
    val latitude: Double,
    val longitude: Double,
    val phone: String
)
