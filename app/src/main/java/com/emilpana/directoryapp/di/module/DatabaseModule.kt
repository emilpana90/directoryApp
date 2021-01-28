package com.emilpana.directoryapp.di.module

import android.content.Context
import androidx.room.Room
import com.emilpana.directoryapp.data.local.database.Database
import com.emilpana.directoryapp.data.local.database.DaoProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DatabaseModule {
    @Binds
    abstract fun provideDatabaseProvider(database: Database): DaoProvider
}

@Module
@InstallIn(ActivityRetainedComponent::class)
object DatabaseModuleProvider {
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): Database {
        return Room.databaseBuilder(
            applicationContext,
            Database::class.java, "directory_db"
        ).build()
    }
}
