package com.emilpana.directoryapp.mock

import com.emilpana.directoryapp.data.remote.ApiService
import com.emilpana.directoryapp.data.remote.entity.RemotePerson
import com.emilpana.directoryapp.data.remote.entity.RemoteRoom
import io.reactivex.rxjava3.core.Single

object MockApiService : ApiService {
    override fun getPeople(): Single<List<RemotePerson>> {
        return Single.just(
            listOf(
                RemotePerson(
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
                RemotePerson(
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
        )
    }

    override fun getRooms(): Single<List<RemoteRoom>> {
        return Single.just(
            listOf(
                RemoteRoom(
                    createdAt = "createdAt1",
                    id = "id2",
                    isOccupied = false,
                    maxOccupancy = 1,
                    name = "nam1"
                ),
                RemoteRoom(
                    createdAt = "createdAt2",
                    id = "id2",
                    isOccupied = true,
                    maxOccupancy = 2,
                    name = "name2"
                )
            )
        )
    }
}
