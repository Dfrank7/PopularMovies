package com.francis.popularmovies.data.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PopularMovieData(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val adult: Boolean,
    val backdrop_path: String,
//    @TypeConverters(GenreTypeConverter::class)
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
