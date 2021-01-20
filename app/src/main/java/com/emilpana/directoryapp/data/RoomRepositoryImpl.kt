package com.emilpana.directoryapp.data

import com.emilpana.directoryapp.data.local.database.DatabaseProvider
import com.emilpana.directoryapp.data.local.mapper.fromLocalRoom
import com.emilpana.directoryapp.data.local.mapper.toLocalRoom
import com.emilpana.directoryapp.data.remote.ApiService
import com.emilpana.directoryapp.data.remote.mapper.fromRemoteRoom
import com.emilpana.directoryapp.domain.entity.model.Room
import com.emilpana.directoryapp.domain.repository.RoomRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    val databaseProvider: DatabaseProvider,
    val apiService: ApiService
) :
    RoomRepository {
    override fun getRemoteRooms(): Single<List<Room>> {
        return apiService.getRooms().map { it.map { room -> room.fromRemoteRoom() } }
    }

    override fun getLocalRooms(): Single<List<Room>> =
        Single.create { emitter ->
            emitter.onSuccess(
                databaseProvider.roomDao().getAllRooms().map { it.fromLocalRoom() })
        }

    override fun replaceLocalRooms(rooms: List<Room>) {
        databaseProvider.roomDao().deleteAllRooms()
        databaseProvider.roomDao().insertAll(rooms.map { it.toLocalRoom() })
    }
}
