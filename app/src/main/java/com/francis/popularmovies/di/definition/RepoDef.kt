package com.francis.popularmovies.di.definition

import com.francis.popularmovies.data.local.IMoviesLocalDatasource
import com.francis.popularmovies.data.remote.IMoviesRemoteDataSource
import com.francis.popularmovies.data.repository.IMoviesRepository
import com.francis.popularmovies.data.repository.MoviesRepository
import com.francis.popularmovies.utility.IAppDispatchers


fun createMoviesRepository(
    moviesLocalDataSource: IMoviesLocalDatasource,
    moviesRemoteDataSource: IMoviesRemoteDataSource,
    iAppDispatchers: IAppDispatchers
): IMoviesRepository{
    return MoviesRepository(moviesRemoteDataSource, moviesLocalDataSource,iAppDispatchers)
}