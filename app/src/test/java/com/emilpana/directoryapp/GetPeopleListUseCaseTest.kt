package com.emilpana.directoryapp

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.emilpana.directoryapp.data.PeopleRepositoryImpl
import com.emilpana.directoryapp.domain.business.GetPeopleListUseCase
import com.emilpana.directoryapp.domain.entity.model.Person
import com.emilpana.directoryapp.domain.entity.model.PersonsListContainer
import com.emilpana.directoryapp.mock.MockApiService
import com.emilpana.directoryapp.mock.MockDatabase
import org.junit.Test


class GetPeopleListUseCaseTest {
    @Test
    fun `given GetPeopleListUseCase, when execute() is called, then the correct data list is retrieved`() {
        val peopleRepository = PeopleRepositoryImpl(MockDatabase, MockApiService)
        val useCase = GetPeopleListUseCase(peopleRepository)
        val peopleList = listOf(
            Person(
                avatar = "avatar1",
                createdAt = "createdAt1",
                email = "email1",
                favouriteColor = "favouriteColor1",
                firstName = "firstName1",
                id = "id1",
                jobTitle = "jobTitle1",
                lastName = "lastName1",
                latitude = 1.0,
                longitude = 2.0,
                phone = "phone1"
            ),
            Person(
                avatar = "avatar2",
                createdAt = "createdAt2",
                email = "email2",
                favouriteColor = "favouriteColor2",
                firstName = "firstName2",
                id = "id2",
                jobTitle = "jobTitle2",
                lastName = "lastName2",
                latitude = 2.0,
                longitude = 3.0,
                phone = "phone2"
            )
        )

        val result = useCase.execute().blockingGet()

        assertThat(result).isEqualTo(PersonsListContainer(peopleList, null, false))
    }
}
