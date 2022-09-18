package com.adamian.themoviedb.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamian.themoviedb.data.network.model.MultiMovieResponse
import com.adamian.themoviedb.data.repository.TheMovieRepositoryImpl
import com.adamian.themoviedb.domain.model.MovieDisplay
import com.adamian.themoviedb.domain.use_case.MovieMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheMovieViewModel @Inject constructor(
    private val theMovieRepositoryImpl: TheMovieRepositoryImpl,
    private val movieMapper: MovieMapper
): ViewModel() {
    private val TAG = "TheMovieViewModel"
    val searchMoviesTvShows: MutableLiveData<MultiMovieResponse> = MutableLiveData()
    val getMovie: MutableLiveData<MovieDisplay> = MutableLiveData()

    fun searchMovies(searchQuery: String) = viewModelScope.launch {
        val response = theMovieRepositoryImpl.searchMoviesTvShows(searchQuery)
        searchMoviesTvShows.postValue(response)
//        Log.d(TAG, "searchMovies: ${response.results}")
    }

    fun getMovieDetails(movieId: String) = viewModelScope.launch {
        movieMapper.invoke(movieId).collectLatest { result -> getMovie.value = result }
    }

}