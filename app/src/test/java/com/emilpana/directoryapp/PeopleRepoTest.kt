package com.emilpana.directoryapp

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import com.emilpana.directoryapp.data.PeopleRepositoryImpl
import com.emilpana.directoryapp.domain.entity.model.Person
import com.emilpana.directoryapp.mock.MockApiService
import com.emilpana.directoryapp.mock.MockDatabase
import org.junit.Test

class PeopleRepoTest {

    @Test
    fun `given PeopleRepository, when getRemotePeople() is called, then the correct people list is retrieved`() {
        val peopleRepository = PeopleRepositoryImpl(MockDatabase, MockApiService)

        val remotePeopleSingle = peopleRepository.getRemotePeople()

        val personsList = remotePeopleSingle.blockingGet()
        assertThat(personsList).containsExactly(
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
    }

    @Test
    fun `given PeopleRepository, when getLocalPeople() is called, then the correct people list is retrieved`() {
        val peopleRepository = PeopleRepositoryImpl(MockDatabase, MockApiService)

        val localPeopleSingle = peopleRepository.getLocalPeople()

        val personsList = localPeopleSingle.blockingGet()
        assertThat(personsList).containsExactly(
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
    }

    @Test
    fun `given PeopleRepository, when getLocalPerson() is called, then the correct person is retrieved`() {
        val peopleRepository = PeopleRepositoryImpl(MockDatabase, MockApiService)
        val personId = "personId"
        val localPeopleSingle = peopleRepository.getLocalPerson(personId)

        val personsList = localPeopleSingle.blockingGet()
        assertThat(personsList).isEqualTo(
            Person(
                avatar = "avatar1",
                createdAt = "createdAt1",
                email = "email1",
                favouriteColor = "favouriteColor1",
                firstName = "firstName1",
                id = "id$personId",
                jobTitle = "jobTitle1",
                lastName = "lastName1",
                latitude = 1.0,
                longitude = 2.0,
                phone = "phone1"
            )
        )
    }
}
