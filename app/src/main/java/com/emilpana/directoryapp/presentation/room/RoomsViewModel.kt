package com.emilpana.directoryapp.presentation.room

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.emilpana.directoryapp.domain.business.GetRoomsListUseCase
import com.emilpana.directoryapp.domain.entity.model.RoomListContainer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomsViewModel @ViewModelInject constructor(private val getRoomsListUseCase: GetRoomsListUseCase) :
    ViewModel() {
    private val refreshLiveDataTrigger = MutableLiveData<Unit>()

    val roomList: LiveData<RoomListContainer> = refreshLiveDataTrigger.switchMap {
        loadRoomsList()
    }

    private fun loadRoomsList(): LiveData<RoomListContainer> {
        val flowable = Flowable
            .fromSingle(getRoomsListUseCase.execute())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return LiveDataReactiveStreams.fromPublisher(flowable)
    }

    fun refreshData() {
        refreshLiveDataTrigger.value = Unit
    }
}
