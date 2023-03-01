package com.francis.popularmovies

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.francis.popularmovies.data.repository.MoviesRepository
import com.francis.popularmovies.home.viewmodel.MoviesViewModel
import com.francis.popularmovies.utility.NetworkStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    private lateinit var moviesViewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val movieRepository : MoviesRepository by lazy {
        Mockito.mock(MoviesRepository::class.java)
    }

    private val networkStatus: NetworkStatus by lazy {
        NetworkStatus(ApplicationProvider.getApplicationContext())
    }

    @Before
    fun setup(){
        moviesViewModel = MoviesViewModel(movieRepository, networkStatus)
    }

    @After
    fun cleanUp() {
        mainCoroutineRule.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }



    @Test
    fun movies_liveData_checkLoading() = mainCoroutineRule.runBlockingTest {
        mainCoroutineRule.pauseDispatcher()

        moviesViewModel.getRemotePopularMovies()
        //checking if livedata is true.

        //extension function in livedatatestutil.
        MatcherAssert.assertThat(
           moviesViewModel.showLoading.getOrAwaitValue(),
            CoreMatchers.`is`(true)
        )

        //after executing, the livedata value should be false.
        mainCoroutineRule.resumeDispatcher()
        MatcherAssert.assertThat(
            moviesViewModel.showLoading.getOrAwaitValue(),
            CoreMatchers.`is`(false)
        )
    }
}