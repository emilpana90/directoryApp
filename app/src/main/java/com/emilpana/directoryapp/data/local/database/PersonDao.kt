package com.emilpana.directoryapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emilpana.directoryapp.data.local.entity.LocalPerson
import io.reactivex.rxjava3.core.Single

@Dao
interface PersonDao {
    @Query("SELECT * FROM persons")
    fun getAllPersons(): List<LocalPerson>

    @Insert
    fun insertAll(person: List<LocalPerson>)

    @Query("DELETE FROM persons")
    fun deleteAllPersons()
}
