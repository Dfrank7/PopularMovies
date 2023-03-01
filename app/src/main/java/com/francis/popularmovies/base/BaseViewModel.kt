package com.francis.popularmovies.base

import androidx.lifecycle.ViewModel
import com.francis.popularmovies.utility.SingleLiveEvent

abstract class BaseViewModel<out T: IBaseRepository>(private val repository: T): ViewModel() {

    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    override fun onCleared() {
        repository.clear()
        super.onCleared()
    }
}