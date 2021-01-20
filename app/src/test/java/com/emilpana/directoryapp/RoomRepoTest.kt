package com.emilpana.directoryapp

import assertk.assertThat
import assertk.assertions.containsExactly
import com.emilpana.directoryapp.data.RoomRepositoryImpl
import com.emilpana.directoryapp.domain.entity.model.Room
import com.emilpana.directoryapp.mock.MockApiService
import com.emilpana.directoryapp.mock.MockDatabase
import org.junit.Test

class RoomRepoTest {

    @Test
    fun `given RoomRepository, when getRemoteRooms() is called, then the correct rooms list is retrieved`() {
        val roomsRepository = RoomRepositoryImpl(MockDatabase, MockApiService)

        val remoteRoomsSingle = roomsRepository.getRemoteRooms()

        val roomsList = remoteRoomsSingle.blockingGet()
        assertThat(roomsList).containsExactly(
            Room(
                createdAt = "createdAt1",
                id = "id2",
                isOccupied = false,
                maxOccupancy = 1,
                name = "nam1"
            ),
            Room(
                createdAt = "createdAt2",
                id = "id2",
                isOccupied = true,
                maxOccupancy = 2,
                name = "name2"
            )
        )
    }

    @Test
    fun `given RoomRepository, when getLocalRooms() is called, then the correct rooms list is retrieved`() {
        val roomsRepository = RoomRepositoryImpl(MockDatabase, MockApiService)

        val remoteRoomsSingle = roomsRepository.getLocalRooms()

        val roomsList = remoteRoomsSingle.blockingGet()
        assertThat(roomsList).containsExactly(
            Room(
                createdAt = "createdAt1",
                id = "id2",
                isOccupied = false,
                maxOccupancy = 1,
                name = "nam1"
            ),
            Room(
                createdAt = "createdAt2",
                id = "id2",
                isOccupied = true,
                maxOccupancy = 2,
                name = "name2"
            )
        )
    }
}
