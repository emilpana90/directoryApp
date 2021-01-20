package com.emilpana.directoryapp.data.local.database

interface DatabaseProvider {
    fun personDao(): PersonDao
    fun roomDao(): RoomDao
}
