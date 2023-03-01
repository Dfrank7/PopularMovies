package com.francis.popularmovies.di.definition

import com.francis.popularmovies.data.db.MovieHelper
import com.francis.popularmovies.data.local.IMoviesLocalDatasource
import com.francis.popularmovies.data.local.MoviesLocalDataSource
import com.francis.popularmovies.utility.IAppDispatchers

fun createLocalDataSource(
    movieHelper: MovieHelper,
    iAppDispatchers: IAppDispatchers
): IMoviesLocalDatasource {
    return MoviesLocalDataSource(movieHelper, iAppDispatchers)
}