package com.francis.popularmovies.data.local

import androidx.lifecycle.LiveData
import com.francis.popularmovies.data.db.MovieHelper
import com.francis.popularmovies.data.db.PopularMovieData
import com.francis.popularmovies.home.model.domain.NetworkMoviesContainer
import com.francis.popularmovies.utility.IAppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesLocalDataSource(
    private val movieHelper: MovieHelper,
    private val iAppDispatchers: IAppDispatchers
): IMoviesLocalDatasource, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = iAppDispatchers.ui()

    override fun getPopularMovies(): LiveData<List<PopularMovieData>> {
        return movieHelper.getPopularList()
    }

    override fun savePopularMovies(movies: NetworkMoviesContainer) {
        launch {
            movieHelper.savePopularList(movies)
        }
    }

    override fun deletePopular() {
        movieHelper.deletePopular()
    }

    override fun clear() {
        coroutineContext.cancel()
    }


}