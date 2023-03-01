package com.francis.popularmovies.home.model.domain

import com.francis.popularmovies.home.model.Movie
import com.francis.popularmovies.data.db.PopularMovieData


data class NetworkMoviesContainer(val result: List<Movie>)
data class PopularMoviesContainer(val popularMovies: List<PopularMovieData>)

fun NetworkMoviesContainer.toPopulatEntity(): Array<PopularMovieData>{
    return result.map {
        PopularMovieData(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
            genre_ids = it.genre_ids,
            id = it.id,
            original_language = it.original_language,
            original_title = it.original_title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            release_date = it.release_date,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count
        )
    }.toTypedArray()
}

fun PopularMoviesContainer.toMovie() : List<Movie>{
    return popularMovies.map {
        Movie(
            it.adult, it.backdrop_path, it.genre_ids, it.id, it.original_language, it.original_title, it.overview, it.popularity,
            it.poster_path, it.release_date, it.original_title, it.video, it.vote_average, it.vote_count
        )
    }
}
