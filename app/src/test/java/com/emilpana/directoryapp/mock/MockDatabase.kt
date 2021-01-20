package com.emilpana.directoryapp.mock

import com.emilpana.directoryapp.data.local.database.DatabaseProvider
import com.emilpana.directoryapp.data.local.database.PersonDao
import com.emilpana.directoryapp.data.local.database.RoomDao
import com.emilpana.directoryapp.data.local.entity.LocalPerson
import com.emilpana.directoryapp.data.local.entity.LocalRoom

object MockDatabase : DatabaseProvider {
    override fun personDao(): PersonDao {
        return object : PersonDao {
            override fun getAllPersons(): List<LocalPerson> {
                return listOf(
                    LocalPerson(
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
                    LocalPerson(
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

            override fun insertAll(person: List<LocalPerson>) {

            }

            override fun deleteAllPersons() {

            }

            override fun getPersonById(personId: String): LocalPerson {
                return LocalPerson(
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
            }
        }
    }

    override fun roomDao(): RoomDao {
        return object : RoomDao {
            override fun getAllRooms(): List<LocalRoom> {
                return listOf(
                    LocalRoom(
                        createdAt = "createdAt1",
                        id = "id2",
                        isOccupied = false,
                        maxOccupancy = 1,
                        name = "nam1"
                    ),
                    LocalRoom(
                        createdAt = "createdAt2",
                        id = "id2",
                        isOccupied = true,
                        maxOccupancy = 2,
                        name = "name2"
                    )
                )
            }

            override fun insertAll(person: List<LocalRoom>) {

            }

            override fun deleteAllRooms() {

            }
        }
    }
}