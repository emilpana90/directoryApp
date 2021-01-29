package com.emilpana.directoryapp.presentation

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {
    fun getIOScheduler(): Scheduler
    fun getMainScheduler(): Scheduler
}
