package com.francis.popularmovies.data.repository

import androidx.lifecycle.LiveData
import com.francis.popularmovies.home.model.MoviesResponse
import com.francis.popularmovies.base.IBaseRepository
import com.francis.popularmovies.data.db.PopularMovieData
import com.francis.popularmovies.home.model.domain.NetworkMoviesContainer

interface IMoviesRepository : IBaseRepository {

    fun getRemotePopularMovies(successCallback: (MoviesResponse)-> Unit, errorCallback: (String)-> Unit)

    fun getSavedPopularMovieList(): LiveData<List<PopularMovieData>>

    fun savePopularList(popularMovieData: NetworkMoviesContainer)

    fun deletePopular()


}