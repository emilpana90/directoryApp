/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.di.module

import android.content.Context
import androidx.room.Room
import com.emilpana.directoryapp.data.local.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): Database {
        return Room.databaseBuilder(
            applicationContext,
            Database::class.java, "directory_db"
        ).build()
    }
}
