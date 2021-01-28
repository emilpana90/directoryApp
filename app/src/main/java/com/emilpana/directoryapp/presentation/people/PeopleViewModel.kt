package com.emilpana.directoryapp.presentation.people

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.emilpana.directoryapp.domain.business.GetPeopleListUseCase
import com.emilpana.directoryapp.domain.entity.model.PersonsListContainer
import com.emilpana.directoryapp.presentation.SchedulerProvider
import io.reactivex.rxjava3.core.Flowable

class PeopleViewModel @ViewModelInject constructor(
    private val getPeopleListUseCase: GetPeopleListUseCase,
    private val schedulerProvider: SchedulerProvider
) :
    ViewModel() {
    val refreshLiveDataTrigger = MutableLiveData<Unit>()

    val peopleList: LiveData<PersonsListContainer> = refreshLiveDataTrigger.switchMap {
        loadPeopleList()
    }

    private fun loadPeopleList(): LiveData<PersonsListContainer> {
        val flowable = Flowable
            .fromSingle(getPeopleListUseCase.execute())
            .subscribeOn(schedulerProvider.getIOScheduler())
            .observeOn(schedulerProvider.getMainScheduler())
        return LiveDataReactiveStreams.fromPublisher(flowable)
    }

    fun refreshData() {
        refreshLiveDataTrigger.value = Unit
    }
}


/**
 * A generic class that contains data and status about loading this data.
 */
/*
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}*/
