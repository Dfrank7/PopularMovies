package com.francis.popularmovies.di

import android.app.Application
import com.francis.popularmovies.data.db.MovieHelper
import com.francis.popularmovies.data.db.MoviesDb
import com.francis.popularmovies.utility.*

fun createMovieHelper(app: Application): MovieHelper{
    val database = MoviesDb.getInstance(app)
    return MovieHelper(database)
}

fun createCoroutineDispatcher(): IAppDispatchers = AppDispatchers()

fun createNetworkStatus(app: Application): INetworkStatus = NetworkStatus(app)

