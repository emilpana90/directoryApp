/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data.remote.entity

import com.google.gson.annotations.SerializedName

data class RemoteRoom(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_occupied")
    val isOccupied: Boolean,
    @SerializedName("max_occupancy")
    val maxOccupancy: Int,
    @SerializedName("name")
    val name: String
)
