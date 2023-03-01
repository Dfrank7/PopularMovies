package com.francis.popularmovies.data.local

import androidx.lifecycle.LiveData
import com.francis.popularmovies.data.IDataSource
import com.francis.popularmovies.data.db.PopularMovieData
import com.francis.popularmovies.home.model.domain.NetworkMoviesContainer

interface IMoviesLocalDatasource : IDataSource{
    fun getPopularMovies(): LiveData<List<PopularMovieData>>
    fun savePopularMovies(movies: NetworkMoviesContainer)
    fun deletePopular()
}