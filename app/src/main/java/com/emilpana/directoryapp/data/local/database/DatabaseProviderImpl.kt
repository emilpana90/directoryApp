/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data.local.database

import javax.inject.Inject

class DatabaseProviderImpl @Inject constructor(val database: Database) : DatabaseProvider {
    override fun personDao(): PersonDao = database.personDao()

    override fun roomDao(): RoomDao = database.roomDao()
}