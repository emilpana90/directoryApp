package com.emilpana.directoryapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emilpana.directoryapp.data.local.entity.LocalPerson
import com.emilpana.directoryapp.data.local.entity.LocalRoom

@Database(
    entities = arrayOf(LocalPerson::class, LocalRoom::class),
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase(), DaoProvider {
     abstract override fun personDao(): PersonDao
    abstract override fun roomDao(): RoomDao
}
