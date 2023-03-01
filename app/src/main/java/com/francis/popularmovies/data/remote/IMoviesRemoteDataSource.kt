package com.francis.popularmovies.data.remote

import com.francis.popularmovies.home.model.MoviesResponse
import com.francis.popularmovies.data.IDataSource

interface IMoviesRemoteDataSource : IDataSource {

    fun getPopularMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (Exception) -> Unit
    )
}