/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.presentation.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emilpana.directoryapp.domain.entity.Room

class RoomsViewModel : ViewModel() {
    val roomList: LiveData<List<Room>> by lazy {
        MutableLiveData<List<Room>>().also {

        }
    }
}
