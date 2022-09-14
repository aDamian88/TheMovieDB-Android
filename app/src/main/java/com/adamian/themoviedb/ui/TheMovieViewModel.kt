package com.adamian.themoviedb.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamian.themoviedb.data.network.model.MultiMovieResponse
import com.adamian.themoviedb.data.repository.TheMovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheMovieViewModel @Inject constructor(
    private val theMovieRepositoryImpl: TheMovieRepositoryImpl
): ViewModel() {
    private val TAG = "TheMovieViewModel"
    val searchMoviesTvShows: MutableLiveData<MultiMovieResponse> = MutableLiveData()

    fun searchMovies(searchQuery: String) = viewModelScope.launch {
        val response = theMovieRepositoryImpl.searchMoviesTvShows(searchQuery);
        Log.d(TAG, "searchMovies: ${response.results}")
    }

}