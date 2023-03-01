package com.francis.popularmovies.data.remote

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.francis.popularmovies.home.model.Movie
import com.francis.popularmovies.home.model.MoviesResponse
import com.francis.popularmovies.service.api.IMoviesService
import com.francis.popularmovies.utility.AppDispatchers
import com.francis.popularmovies.utility.IAppDispatchers
import com.francis.popularmovies.utility.NetworkStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class MoviesRemoteDataSourceTest {

    private lateinit var remoteDataSource: MoviesRemoteDataSource
    private val coroutineDispatcher: IAppDispatchers = AppDispatchers()

    private val moviesService : IMoviesService by lazy {
        Mockito.mock(IMoviesService::class.java)
    }

    private val networkStatus: NetworkStatus by lazy {
        NetworkStatus(ApplicationProvider.getApplicationContext())
    }

    @Before
    fun setUp(){
        remoteDataSource = MoviesRemoteDataSource(moviesService, coroutineDispatcher, networkStatus)
    }

    @After
    fun tearDown() = coroutineDispatcher.default().cancel()



    @Test
    fun test_that_remote_source_can_fetch_popular_movie_list() : Unit = runBlockingTest {
        val movieA =  Movie(false, "test1", listOf(1,2,3),1, "test1", "test1",
            "test1", 3.0,"Pix", "2022","TEST1", false, 3.45, 4)

        val movieB =  Movie(false, "test1", listOf(1,2,3),1, "test1", "test1",
            "test1", 3.0,"Pix", "2022","TEST1", false, 3.45, 4)

        val response = listOf(movieA, movieB)
        val movieResponse = MoviesResponse(1,response,1, 1)
        `when`(moviesService.getPopularMovies())
            .thenReturn(Response.success(movieResponse))
        val result = remoteDataSource.getPopularMovies(any(), any())
        Assert.assertNotNull(result)
    }

}