package com.francis.popularmovies.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.francis.popularmovies.home.model.Movie
import com.francis.popularmovies.base.BaseViewModel
import com.francis.popularmovies.home.model.domain.PopularMoviesContainer
import com.francis.popularmovies.home.model.domain.toMovie
import com.francis.popularmovies.data.repository.IMoviesRepository
import com.francis.popularmovies.home.model.Response
import com.francis.popularmovies.utility.INetworkStatus
import kotlinx.coroutines.launch



class MoviesViewModel(
    private val moviesRepository: IMoviesRepository,
    private val networkStatus: INetworkStatus
) : BaseViewModel<IMoviesRepository>(moviesRepository) {

    private val _status = MutableLiveData<MovieAPIStatus>()
    val status: LiveData<MovieAPIStatus> get() = _status

    private val _movieList = MutableLiveData<List<Movie>>()
    val refreshmovieList : LiveData<List<Movie>>
        get() = _movieList
    private lateinit var movies : LiveData<List<Movie>>

    private val _navigateToDetails = MutableLiveData<Movie>()
    val navigateToDetails:LiveData<Movie>
        get() = _navigateToDetails

    private val _checkInternet = MutableLiveData<Boolean>()
    val checkInternet : LiveData<Boolean>
        get() = _checkInternet

    init {
        viewModelScope.launch {
            try {
                showLoading.value = true
                refreshMovies()
            }catch (e:Exception){
                showLoading.value = false
            }finally {
                showLoading.value = false
            }
        }
        viewModelScope.launch {
            _checkInternet.value = networkStatus.isConnected()
        }
    }

    fun refreshMovies(){
        getRemotePopularMovies()
    }

    fun getRemotePopularMovies(){
         showLoading.value = true
        viewModelScope.launch {
            _status.postValue(MovieAPIStatus.LOADING)
            moviesRepository.getRemotePopularMovies(
                successCallback = {
                    // _movieList.postValue(it.results)
                    _status.postValue(MovieAPIStatus.DONE)
                    Response(true, null)
                },
                errorCallback = {
                    _status.postValue(MovieAPIStatus.ERROR)
                    Response(false, it)

                }
            )
              showLoading.postValue(false)
        }
    }

    fun getSavedPopularMovies(): LiveData<List<Movie>>{
        viewModelScope.launch {
            val movi= moviesRepository.getSavedPopularMovieList()
            movies = Transformations.map(movi){
                PopularMoviesContainer(it).toMovie()
            }
        }
        return movies
    }

    fun onMovieClicked(movie: Movie) {
        _navigateToDetails.value = movie
    }

    fun onMovieCompleteNavigation(){
        _navigateToDetails.value = null
    }



    enum class MovieAPIStatus {
        LOADING,
        ERROR,
        DONE
    }
}
