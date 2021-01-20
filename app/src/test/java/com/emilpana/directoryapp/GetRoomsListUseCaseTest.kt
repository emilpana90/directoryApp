package com.emilpana.directoryapp

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.emilpana.directoryapp.data.RoomRepositoryImpl
import com.emilpana.directoryapp.domain.business.GetRoomsListUseCase
import com.emilpana.directoryapp.domain.entity.model.Room
import com.emilpana.directoryapp.domain.entity.model.RoomListContainer
import com.emilpana.directoryapp.mock.MockApiService
import com.emilpana.directoryapp.mock.MockDatabase
import org.junit.Test

class GetRoomsListUseCaseTest {

    @Test
    fun `given GetRoomsListUseCase, when execute() is called, then the correct data list is retrieved`() {
        val roomRepository = RoomRepositoryImpl(MockDatabase, MockApiService)
        val useCase = GetRoomsListUseCase(roomRepository)
        val roomsList = listOf(
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

        val result = useCase.execute().blockingGet()

        assertThat(result).isEqualTo(RoomListContainer(roomsList, null, false))
    }
}
