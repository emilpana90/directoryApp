/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.presentation.people

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.emilpana.directoryapp.domain.business.GetPeopleListUseCase
import com.emilpana.directoryapp.domain.entity.model.PersonsListContainer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class PeopleViewModel @ViewModelInject constructor(private val getPeopleListUseCase: GetPeopleListUseCase) :
    ViewModel() {
    private val refreshLiveDataTrigger = MutableLiveData<Unit>()

    val peopleList: LiveData<PersonsListContainer> = refreshLiveDataTrigger.switchMap {
        loadPeopleList()
    }

    private fun loadPeopleList(): LiveData<PersonsListContainer> {
        val flowable = Flowable
            .fromSingle(getPeopleListUseCase.execute())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return LiveDataReactiveStreams.fromPublisher(flowable)
    }

    fun refreshData() {
        refreshLiveDataTrigger.value = Unit
    }
}
