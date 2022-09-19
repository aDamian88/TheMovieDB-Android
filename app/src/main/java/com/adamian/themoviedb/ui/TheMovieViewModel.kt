package com.adamian.themoviedb.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamian.themoviedb.data.network.model.MultiMovieResponse
import com.adamian.themoviedb.data.repository.TheMovieRepositoryImpl
import com.adamian.themoviedb.domain.model.MovieTvShowDisplay
import com.adamian.themoviedb.domain.use_case.MovieMapper
import com.adamian.themoviedb.domain.use_case.MovieTvShowEntityMapper
import com.adamian.themoviedb.domain.use_case.SaveDeleteMovieTvShow
import com.adamian.themoviedb.domain.use_case.TvShowMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheMovieViewModel @Inject constructor(
    private val theMovieRepositoryImpl: TheMovieRepositoryImpl,
    private val movieMapper: MovieMapper,
    private val tvShowMapper: TvShowMapper,
    private val movieTvShowEntityMapper: MovieTvShowEntityMapper,
    private val saveDeleteMovieTvShow: SaveDeleteMovieTvShow
) : ViewModel() {
    private val TAG = "TheMovieViewModel"
    val searchMoviesTvShows: MutableLiveData<MultiMovieResponse> = MutableLiveData()
    val getMovieTvShow: MutableLiveData<MovieTvShowDisplay> = MutableLiveData()

    fun searchMoviesTvShowsBar(searchQuery: String) = viewModelScope.launch {
        val response = theMovieRepositoryImpl.searchMoviesTvShows(searchQuery)
        searchMoviesTvShows.postValue(response)
    }

    fun getMovieTvShowDetails(id: String, type: String) = viewModelScope.launch {
        if (type == "movie") {
            movieMapper.invoke(id).collectLatest { result -> getMovieTvShow.value = result }
        } else if (type == "tv") {
            tvShowMapper.invoke(id).collectLatest { result -> getMovieTvShow.value = result }
        }
    }

    fun onClickDatabaseButton(movieTvShow: MovieTvShowDisplay, type: String) =
        viewModelScope.launch {
            movieTvShowEntityMapper.invoke(
                movieTvShow,
                type
            ).collectLatest { result ->
                saveDeleteMovieTvShow.invoke(result)
                    .collectLatest { databaseResult -> getMovieTvShow.postValue(databaseResult) }
            }
        }

}