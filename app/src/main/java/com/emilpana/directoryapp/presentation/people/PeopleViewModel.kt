/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.presentation.people

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.emilpana.directoryapp.domain.entity.model.Person
import com.emilpana.directoryapp.domain.entity.model.PersonsListContainer
import com.emilpana.directoryapp.domain.people.PeopleRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class PeopleViewModel @ViewModelInject constructor(private val peopleRepository: PeopleRepository) :
    ViewModel() {
    val peopleList: LiveData<PersonsListContainer> by lazy {
        val flowable = Flowable
            .fromSingle(peopleRepository.getAllPeople())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        LiveDataReactiveStreams.fromPublisher(flowable)
    }
}
