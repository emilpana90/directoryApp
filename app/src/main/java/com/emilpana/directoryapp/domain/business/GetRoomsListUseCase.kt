/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.domain.business

import android.util.Log
import com.emilpana.directoryapp.domain.entity.model.RoomListContainer
import com.emilpana.directoryapp.domain.repository.RoomRepository
import io.reactivex.rxjava3.core.Single
import java.io.IOException
import javax.inject.Inject

class GetRoomsListUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    fun execute(): Single<RoomListContainer> {
        return roomRepository.getRemoteRooms().map { RoomListContainer(it) }.doOnSuccess {
            // Save to db
            it.roomList?.let {
                roomRepository.replaceLocalRooms(it)
            }

            Log.d(GetRoomsListUseCase::class.simpleName, "rooms list loaded")
        }.onErrorResumeNext { error ->
            Log.d(GetRoomsListUseCase::class.simpleName, "rooms list load failed")
            when (error) {
                // If no internet, get from local database
                is IOException -> {
                    // Get from db
                    roomRepository.getLocalRooms()
                        .map { roomsList ->
                            RoomListContainer(
                                roomsList,
                                // Rethrow error if cached list is empty
                                if (roomsList.isEmpty()) error else null
                            )
                        }
                        // Trigger a container in case of error, else the error will not be catched by
                        // livedata reactive streams
                        .onErrorResumeNext { Single.just(RoomListContainer(null, error)) }
                }
                // Trigger a container in case of error, else the error will not be catched by livedata
                // reactive streams
                else -> Single.just(RoomListContainer(null, error))
            }
        }
    }
}
