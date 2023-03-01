package com.francis.popularmovies.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePopularMovies(vararg movieTrans: PopularMovieData)

    @Query("Select * From PopularMovieData")
    fun getPopularMovies(): LiveData<List<PopularMovieData>>

    @Query("DELETE  FROM PopularMovieData")
    fun deletePopularMovies()

    @Query("Select * From PopularMovieData")
    fun getPopularMoviesTest(): PopularMovieData

    @Query("Select * From PopularMovieData")
    fun getAllPopular(): List<PopularMovieData>

}