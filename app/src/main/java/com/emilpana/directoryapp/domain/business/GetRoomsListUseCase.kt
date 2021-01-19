/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.domain.business

import android.util.Log
import com.emilpana.directoryapp.domain.entity.model.RoomListContainer
import com.emilpana.directoryapp.domain.repository.RoomRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRoomsListUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    fun execute(): Single<RoomListContainer> {
        return roomRepository.getAllRooms().map { RoomListContainer(it) }.doOnSuccess {
            Log.d(GetRoomsListUseCase::class.simpleName, "rooms list loaded")
        }.onErrorResumeNext {
            Log.d(GetRoomsListUseCase::class.simpleName, "rooms list load failed")
            Single.just(
                RoomListContainer(null, it)
            )
        }
    }
}
