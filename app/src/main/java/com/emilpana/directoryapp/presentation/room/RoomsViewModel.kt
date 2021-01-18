/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.presentation.room

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.emilpana.directoryapp.domain.entity.model.Room
import com.emilpana.directoryapp.domain.room.RoomRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomsViewModel @ViewModelInject constructor(private val roomRepository: RoomRepository) :
    ViewModel() {
    val roomList: LiveData<List<Room>> by lazy {
        val flowable = Flowable
            .fromSingle(roomRepository.getAllRooms())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        LiveDataReactiveStreams.fromPublisher(flowable)
    }
}
