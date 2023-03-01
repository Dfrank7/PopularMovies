package com.francis.popularmovies.di.definition

import com.francis.popularmovies.service.api.IMoviesService
import com.francis.popularmovies.utility.Constants
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun createMoviesService(okHttpClient: OkHttpClient): IMoviesService{
    val retrofit = buildRetrofit(okHttpClient, Constants.BASE_URL)
    return retrofit.create(IMoviesService::class.java)
}


private fun buildRetrofit(client: OkHttpClient, baseUrl: String) : Retrofit{
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(client)
        .build()
}