package com.emilpana.directoryapp.domain.business

import com.emilpana.directoryapp.domain.entity.model.RoomListContainer
import com.emilpana.directoryapp.domain.repository.RoomRepository
import io.reactivex.rxjava3.core.Single
import java.io.IOException
import javax.inject.Inject

class GetRoomsListUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    fun execute(): Single<RoomListContainer> {
        return roomRepository.getRemoteRooms().map { RoomListContainer(it, isDataOld = false) }
            .doOnSuccess {
                // Save to db
                it.roomList?.let {
                    roomRepository.replaceLocalRooms(it)
                }
            }.onErrorResumeNext { error ->
                when (error) {
                    // If no internet, get from local database
                    is IOException -> {
                        // Get from db
                        roomRepository.getLocalRooms()
                            .map { roomsList ->
                                if (roomsList.isEmpty()) {
                                    RoomListContainer(
                                        null,
                                        // Rethrow error if cached list is empty
                                        error,
                                        isDataOld = false
                                    )
                                } else {
                                    RoomListContainer(
                                        roomsList,
                                        null,
                                        isDataOld = true
                                    )
                                }
                            }
                            // Trigger a container in case of error, else the error will not be catched by
                            // livedata reactive streams
                            .onErrorResumeNext {
                                Single.just(
                                    RoomListContainer(
                                        null,
                                        error,
                                        false
                                    )
                                )
                            }
                    }
                    // Trigger a container in case of error, else the error will not be catched by livedata
                    // reactive streams
                    else -> Single.just(RoomListContainer(null, error, false))
                }
            }
    }
}
