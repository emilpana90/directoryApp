package com.emilpana.directoryapp

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.emilpana.directoryapp.data.local.entity.LocalPerson
import com.emilpana.directoryapp.data.local.entity.LocalRoom
import com.emilpana.directoryapp.data.local.mapper.fromLocalPerson
import com.emilpana.directoryapp.data.local.mapper.fromLocalRoom
import com.emilpana.directoryapp.data.local.mapper.toLocalPerson
import com.emilpana.directoryapp.data.local.mapper.toLocalRoom
import com.emilpana.directoryapp.data.remote.entity.RemotePerson
import com.emilpana.directoryapp.data.remote.entity.RemoteRoom
import com.emilpana.directoryapp.data.remote.mapper.fromRemotePerson
import com.emilpana.directoryapp.data.remote.mapper.fromRemoteRoom
import com.emilpana.directoryapp.data.remote.mapper.toRemotePerson
import com.emilpana.directoryapp.data.remote.mapper.toRemoteRoom
import com.emilpana.directoryapp.domain.entity.model.Person
import com.emilpana.directoryapp.domain.entity.model.Room
import org.junit.Test

/**
 * Test all data mappers between layers
 */
class MappersUnitTest {

    @Test
    fun `given a RemotePerson, when we map it, then the correct Person is retrieved`() {
        // Given
        val remotePerson = RemotePerson(
            avatar = "avatar",
            createdAt = "createdAt",
            email = "email",
            favouriteColor = "favouriteColor",
            firstName = "firstName",
            id = "id",
            jobTitle = "jobTitle",
            lastName = "lastName",
            latitude = 1.0,
            longitude = 2.0,
            phone = "phone"
        )

        // When
        val person = remotePerson.fromRemotePerson()

        // Then
        assertThat(person).isEqualTo(
            Person(
                avatar = "avatar",
                createdAt = "createdAt",
                email = "email",
                favouriteColor = "favouriteColor",
                firstName = "firstName",
                id = "id",
                jobTitle = "jobTitle",
                lastName = "lastName",
                latitude = 1.0,
                longitude = 2.0,
                phone = "phone"
            )
        )
    }

    @Test
    fun `given a Person, when we map it, then the correct RemotePerson is retrieved`() {
        // Given
        val person = Person(
            avatar = "avatar",
            createdAt = "createdAt",
            email = "email",
            favouriteColor = "favouriteColor",
            firstName = "firstName",
            id = "id",
            jobTitle = "jobTitle",
            lastName = "lastName",
            latitude = 1.0,
            longitude = 2.0,
            phone = "phone"
        )

        // When
        val remotePerson = person.toRemotePerson()

        // Then
        assertThat(remotePerson).isEqualTo(
            RemotePerson(
                avatar = "avatar",
                createdAt = "createdAt",
                email = "email",
                favouriteColor = "favouriteColor",
                firstName = "firstName",
                id = "id",
                jobTitle = "jobTitle",
                lastName = "lastName",
                latitude = 1.0,
                longitude = 2.0,
                phone = "phone"
            )
        )
    }

    @Test
    fun `given a LocalPerson, when we map it, then the correct Person is retrieved`() {
        // Given
        val localPerson = LocalPerson(
            avatar = "avatar",
            createdAt = "createdAt",
            email = "email",
            favouriteColor = "favouriteColor",
            firstName = "firstName",
            id = "id",
            jobTitle = "jobTitle",
            lastName = "lastName",
            latitude = 1.0,
            longitude = 2.0,
            phone = "phone"
        )

        // When
        val person = localPerson.fromLocalPerson()

        // Then
        assertThat(person).isEqualTo(
            Person(
                avatar = "avatar",
                createdAt = "createdAt",
                email = "email",
                favouriteColor = "favouriteColor",
                firstName = "firstName",
                id = "id",
                jobTitle = "jobTitle",
                lastName = "lastName",
                latitude = 1.0,
                longitude = 2.0,
                phone = "phone"
            )
        )
    }

    @Test
    fun `given a Person, when we map it, then the correct LocalPerson is retrieved`() {
        // Given
        val person = Person(
            avatar = "avatar",
            createdAt = "createdAt",
            email = "email",
            favouriteColor = "favouriteColor",
            firstName = "firstName",
            id = "id",
            jobTitle = "jobTitle",
            lastName = "lastName",
            latitude = 1.0,
            longitude = 2.0,
            phone = "phone"
        )

        // When
        val localPerson = person.toLocalPerson()

        // Then
        assertThat(localPerson).isEqualTo(
            LocalPerson(
                avatar = "avatar",
                createdAt = "createdAt",
                email = "email",
                favouriteColor = "favouriteColor",
                firstName = "firstName",
                id = "id",
                jobTitle = "jobTitle",
                lastName = "lastName",
                latitude = 1.0,
                longitude = 2.0,
                phone = "phone"
            )
        )
    }

    @Test
    fun `given a RemoteRoom, when we map it, then the correct Room is retrieved`() {
        // Given
        val remoteRoom = RemoteRoom(
            createdAt = "createdAt",
            id = "id",
            isOccupied = false,
            maxOccupancy = 1,
            name = "name"
        )

        // When
        val room = remoteRoom.fromRemoteRoom()

        // Then
        assertThat(room).isEqualTo(
            Room(
                createdAt = "createdAt",
                id = "id",
                isOccupied = false,
                maxOccupancy = 1,
                name = "name"
            )
        )
    }

    @Test
    fun `given a Room, when we map it, then the correct RemoteRoom is retrieved`() {
        // Given
        val room = Room(
            createdAt = "createdAt",
            id = "id",
            isOccupied = false,
            maxOccupancy = 1,
            name = "name"
        )

        // When
        val remoteRoom = room.toRemoteRoom()

        // Then
        assertThat(remoteRoom).isEqualTo(
            RemoteRoom(
                createdAt = "createdAt",
                id = "id",
                isOccupied = false,
                maxOccupancy = 1,
                name = "name"
            )
        )
    }

    @Test
    fun `given a LocalRoom, when we map it, then the correct Room is retrieved`() {
        // Given
        val localRoom = LocalRoom(
            createdAt = "createdAt",
            id = "id",
            isOccupied = false,
            maxOccupancy = 1,
            name = "name"
        )

        // When
        val room = localRoom.fromLocalRoom()

        // Then
        assertThat(room).isEqualTo(
            Room(
                createdAt = "createdAt",
                id = "id",
                isOccupied = false,
                maxOccupancy = 1,
                name = "name"
            )
        )
    }

    @Test
    fun `given a Room, when we map it, then the correct LocalRoom is retrieved`() {
        // Given
        val room = Room(
            createdAt = "createdAt",
            id = "id",
            isOccupied = false,
            maxOccupancy = 1,
            name = "name"
        )

        // When
        val localRoom = room.toLocalRoom()

        // Then
        assertThat(localRoom).isEqualTo(
            LocalRoom(
                createdAt = "createdAt",
                id = "id",
                isOccupied = false,
                maxOccupancy = 1,
                name = "name"
            )
        )
    }
}
