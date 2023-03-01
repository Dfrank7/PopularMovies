package com.francis.popularmovies.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.francis.popularmovies.home.model.domain.GenreTypeConverter

@Database(entities = [PopularMovieData::class], version = 1)
@TypeConverters(GenreTypeConverter::class)
abstract class MoviesDb : RoomDatabase() {

    abstract fun getMoviesDb(): MoviesDao

    companion object {
        @Volatile
        var INSTANCE: MoviesDb? = null

        fun getInstance(context: Context): MoviesDb {

            val tempInstance = INSTANCE

            if (tempInstance != null) {

                return tempInstance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(context.applicationContext,
                    MoviesDb::class.java, "popular_movies_db")
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance

                return instance
            }
        }


    }

}