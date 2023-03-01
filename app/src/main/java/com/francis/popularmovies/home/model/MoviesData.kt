package com.francis.popularmovies.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

@Parcelize
data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
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
): Parcelable

//data class Error(val success: Boolean, val status_code: Int, val status_message: String)
data class Response(val success: Boolean, val error_message: String?)
