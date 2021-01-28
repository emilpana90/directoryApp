package com.emilpana.directoryapp.data.local.database

interface DaoProvider {
    fun personDao(): PersonDao
    fun roomDao(): RoomDao
}
