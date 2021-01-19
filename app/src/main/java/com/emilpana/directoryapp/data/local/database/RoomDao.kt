package com.emilpana.directoryapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emilpana.directoryapp.data.local.entity.LocalRoom

@Dao
interface RoomDao {
    @Query("SELECT * FROM rooms")
    fun getAllRooms(): List<LocalRoom>

    @Insert
    fun insertAll(person: List<LocalRoom>)

    @Query("DELETE FROM rooms")
    fun deleteAllRooms()
}
