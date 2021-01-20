/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.data.local.database

interface DatabaseProvider {
    fun personDao(): PersonDao
    fun roomDao(): RoomDao
}
