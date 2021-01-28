package com.emilpana.directoryapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emilpana.directoryapp.data.local.entity.LocalRoom
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RoomDao {
    @Query("SELECT * FROM rooms")
    fun getAllRooms(): Single<List<LocalRoom>>

    @Insert
    fun insertAll(person: List<LocalRoom>): Completable

    @Query("DELETE FROM rooms")
    fun deleteAllRooms(): Completable
}
