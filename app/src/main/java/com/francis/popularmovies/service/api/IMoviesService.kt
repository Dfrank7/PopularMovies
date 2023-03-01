package com.francis.popularmovies.service.api

import com.francis.popularmovies.home.model.MoviesResponse
import com.francis.popularmovies.utility.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMoviesService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String? = Constants.apiKey): Response<MoviesResponse>
}