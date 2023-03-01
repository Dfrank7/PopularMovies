package com.francis.popularmovies.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.francis.popularmovies.data.db.MoviesDao
import com.francis.popularmovies.data.db.MoviesDb
import com.francis.popularmovies.data.db.PopularMovieData
import com.francis.popularmovies.home.model.Movie
import com.francis.popularmovies.home.model.domain.NetworkMoviesContainer
import com.francis.popularmovies.home.model.domain.toPopulatEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Unit test the DAO
@SmallTest
class MovieDaoTest {

    private lateinit var database: MoviesDb
    private lateinit var movieDao: MoviesDao

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MoviesDb::class.java
        ).build()
        movieDao = database.getMoviesDb()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun test_that_movieDao_can_save_PopularMovies() = runBlockingTest{
        val genre_ids = listOf(1,2,3)
        val movie = Movie(false, "test1", genre_ids,1, "test1", "test1",
            "test1", 3.0,"Pix", "2022","TEST1", false, 3.45, 4)


        val data = NetworkMoviesContainer(listOf(movie))
        //Saving data to database
        movieDao.savePopularMovies(*data.toPopulatEntity())

        //Retrieving data from database
        val dataFromSource = movieDao.getAllPopular()
        assertEquals(1, dataFromSource.size)
    }

    @Test
    fun insertMovies_RetrieveFromDb_BothareSame() = runBlockingTest {
        val genre_ids = listOf<Int>(1,2,3)
        val movie = PopularMovieData(false, "test", genre_ids,1, "test", "test",
        "test", 3.0,"Pix", "2022","TEST", false, 3.45, 4)
        //Saving data to database

        movieDao.savePopularMovies(movie)

        //Retrieving data from database
        val dataFromSource = database.getMoviesDb().getPopularMoviesTest()

        println(movie.overview + "and "+ dataFromSource.overview)


        assertThat(dataFromSource?.overview, `is`(movie.overview))
        assertThat(dataFromSource?.popularity, `is`(movie.popularity))

    }

}