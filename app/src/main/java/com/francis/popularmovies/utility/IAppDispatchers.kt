package com.francis.popularmovies.utility

import kotlinx.coroutines.CoroutineDispatcher

interface IAppDispatchers {

    fun ui(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun default(): CoroutineDispatcher
}