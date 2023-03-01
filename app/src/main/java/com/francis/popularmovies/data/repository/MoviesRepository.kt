package com.francis.popularmovies.data.repository

import androidx.lifecycle.LiveData
import com.francis.popularmovies.home.model.MoviesResponse
import com.francis.popularmovies.data.db.PopularMovieData
import com.francis.popularmovies.home.model.domain.NetworkMoviesContainer
import com.francis.popularmovies.data.local.IMoviesLocalDatasource
import com.francis.popularmovies.data.remote.IMoviesRemoteDataSource
import com.francis.popularmovies.utility.IAppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesRepository(
    private val moviesRemoteDataSource: IMoviesRemoteDataSource,
    private val moviesLocalDataSource: IMoviesLocalDatasource,
    private val iAppDispatchers: IAppDispatchers
):IMoviesRepository, CoroutineScope {
    override fun getRemotePopularMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (String) -> Unit
    ) {
        launch {
            moviesRemoteDataSource.getPopularMovies(
                { response ->
                    val popularMovieData = NetworkMoviesContainer(response.results)
                    deletePopular()
                    savePopularList(popularMovieData)
                    successCallback.invoke(response)

                },

                {ex ->
                    errorCallback.invoke(ex.message ?: "Unknown error occured" )

                }
            )
        }
    }

    override fun getSavedPopularMovieList(): LiveData<List<PopularMovieData>> {
        return moviesLocalDataSource.getPopularMovies()
    }

    override fun savePopularList(popularMovieData: NetworkMoviesContainer) {
        return moviesLocalDataSource.savePopularMovies(popularMovieData)
    }

    override fun deletePopular() {
        return moviesLocalDataSource.deletePopular()
    }


    override fun clear() {
        moviesLocalDataSource.clear()
        moviesRemoteDataSource.clear()
        coroutineContext.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = iAppDispatchers.ui()
}