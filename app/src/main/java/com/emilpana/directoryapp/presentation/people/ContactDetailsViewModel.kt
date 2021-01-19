/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.presentation.people

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.emilpana.directoryapp.domain.business.GetPersonDetailsUseCase
import com.emilpana.directoryapp.domain.entity.model.PersonContainer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class ContactDetailsViewModel @ViewModelInject constructor(
    @Assisted val savedStateHandle: SavedStateHandle,
    val getPersonDetailsUseCase: GetPersonDetailsUseCase
) : ViewModel() {

    fun setPersonId(personId: String) {
        savedStateHandle["personId"] = personId
    }

    val personLiveData = savedStateHandle.getLiveData<String>("personId").switchMap {
        Log.i(ContactDetailsViewModel::class.simpleName, "received $it name from savedStateHandle")
        loadPersonDetails(it)
    }

    private fun loadPersonDetails(personId: String): LiveData<PersonContainer> {
        val flowable = Flowable
            .fromSingle(getPersonDetailsUseCase.execute(personId))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return LiveDataReactiveStreams.fromPublisher(flowable)
    }
}
