package com.emilpana.directoryapp.di.module

import com.emilpana.directoryapp.data.PeopleRepositoryImpl
import com.emilpana.directoryapp.data.RoomRepositoryImpl
import com.emilpana.directoryapp.domain.people.PeopleRepository
import com.emilpana.directoryapp.domain.room.RoomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPeopleRepository(peopleRepositoryImpl: PeopleRepositoryImpl): PeopleRepository

    @Binds
    abstract fun bindRoomsRepository(roomRepositoryImpl: RoomRepositoryImpl): RoomRepository
}
