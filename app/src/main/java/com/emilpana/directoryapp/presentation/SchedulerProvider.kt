/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.presentation

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {
    fun getIOScheduler(): Scheduler
    fun getMainScheduler(): Scheduler
}
