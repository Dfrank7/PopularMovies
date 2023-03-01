package com.francis.popularmovies.data.db

import androidx.lifecycle.LiveData
import com.francis.popularmovies.home.model.domain.NetworkMoviesContainer
import com.francis.popularmovies.home.model.domain.toPopulatEntity

class MovieHelper(private val database: MoviesDb) {

    fun getPopularList(): LiveData<List<PopularMovieData>>{
        return database.getMoviesDb().getPopularMovies()
    }

    fun  savePopularList(data: NetworkMoviesContainer){
        database.getMoviesDb().savePopularMovies(*data.toPopulatEntity())
    }

    fun  deletePopular(){
        return database.getMoviesDb().deletePopularMovies()
    }

}