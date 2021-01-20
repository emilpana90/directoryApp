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
