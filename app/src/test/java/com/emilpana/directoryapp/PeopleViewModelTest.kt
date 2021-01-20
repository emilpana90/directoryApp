package com.emilpana.directoryapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.emilpana.directoryapp.data.PeopleRepositoryImpl
import com.emilpana.directoryapp.domain.business.GetPeopleListUseCase
import com.emilpana.directoryapp.domain.entity.model.Person
import com.emilpana.directoryapp.domain.entity.model.PersonsListContainer
import com.emilpana.directoryapp.mock.MockApiService
import com.emilpana.directoryapp.mock.MockDatabase
import com.emilpana.directoryapp.mock.mock
import com.emilpana.directoryapp.presentation.SchedulerProvider
import com.emilpana.directoryapp.presentation.people.PeopleViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.processors.ReplayProcessor
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import java.util.concurrent.TimeUnit


class PeopleViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun `given PeopleViewModel, when , then `() {
        val peopleRepository = PeopleRepositoryImpl(MockDatabase, MockApiService)
        val useCase = GetPeopleListUseCase(peopleRepository)
        val trampoline = Schedulers.trampoline()
        val peopleViewModel = PeopleViewModel(useCase, object : SchedulerProvider {
            override fun getIOScheduler(): Scheduler = trampoline

            override fun getMainScheduler(): Scheduler = trampoline
        })

        val dataObserver = mock<Observer<PersonsListContainer>>()
        peopleViewModel.peopleList.observeForever(dataObserver);

        peopleViewModel.refreshData()

        val list = listOf(
            Person(
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
            Person(
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

        verify(dataObserver, Mockito.times(1))
            .onChanged(PersonsListContainer(list, null, false))
    }

    @Test
    fun `given PeopleViewModel, when refreshData() is called, then the live data updates accordingly`() {
        val peopleRepository = PeopleRepositoryImpl(MockDatabase, MockApiService)
        val useCase = GetPeopleListUseCase(peopleRepository)
        val peopleViewModel = PeopleViewModel(useCase, object : SchedulerProvider {
            override fun getIOScheduler(): Scheduler = TestScheduler()

            override fun getMainScheduler(): Scheduler = TestScheduler()
        })

        val observer = mock<Observer<Unit>>()
        peopleViewModel.refreshLiveDataTrigger.observeForever(observer)

        peopleViewModel.refreshData()

        verify(observer, Mockito.times(1))
            .onChanged(Unit)
    }
}
