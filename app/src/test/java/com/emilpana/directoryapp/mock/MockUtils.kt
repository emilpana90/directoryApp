/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.mock

import org.mockito.Mockito

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
