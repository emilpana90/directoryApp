package com.emilpana.directoryapp.di.module

import com.emilpana.directoryapp.data.PeopleRepositoryImpl
import com.emilpana.directoryapp.data.RoomRepositoryImpl
import com.emilpana.directoryapp.domain.repository.PeopleRepository
import com.emilpana.directoryapp.domain.repository.RoomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPeopleRepository(peopleRepositoryImpl: PeopleRepositoryImpl): PeopleRepository

    @Binds
    abstract fun bindRoomsRepository(roomRepositoryImpl: RoomRepositoryImpl): RoomRepository
}
