package com.francis.popularmovies.di.definition

import com.francis.popularmovies.data.remote.IMoviesRemoteDataSource
import com.francis.popularmovies.data.remote.MoviesRemoteDataSource
import com.francis.popularmovies.service.api.IMoviesService
import com.francis.popularmovies.utility.IAppDispatchers
import com.francis.popularmovies.utility.INetworkStatus

fun createMoviesRemoteDataSource(
    mIMoviesService: IMoviesService,
    iAppDispatchers: IAppDispatchers,
    iNetworkStatus: INetworkStatus
): IMoviesRemoteDataSource {
    return MoviesRemoteDataSource(mIMoviesService, iAppDispatchers, iNetworkStatus)
}