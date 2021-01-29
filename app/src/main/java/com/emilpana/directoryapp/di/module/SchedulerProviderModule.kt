package com.emilpana.directoryapp.di.module

import com.emilpana.directoryapp.presentation.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
@InstallIn(ActivityRetainedComponent::class)
object SchedulerProviderModule {
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return object : SchedulerProvider {
            override fun getIOScheduler(): Scheduler = Schedulers.io()

            override fun getMainScheduler(): Scheduler = AndroidSchedulers.mainThread()
        }
    }
}
