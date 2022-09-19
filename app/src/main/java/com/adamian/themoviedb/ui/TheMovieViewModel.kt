package com.adamian.themoviedb.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamian.themoviedb.data.repository.TheMovieRepositoryImpl
import com.adamian.themoviedb.domain.model.MovieTvShowDisplay
import com.adamian.themoviedb.domain.model.SearchScreenDisplay
import com.adamian.themoviedb.domain.use_case.*
import com.adamian.themoviedb.utils.DataSource
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
    private val saveDeleteMovieTvShow: SaveDeleteMovieTvShow,
    private val dataFromDatabase: DataFromDatabase
) : ViewModel() {
    val searchMoviesTvShows: MutableLiveData<SearchScreenDisplay> = MutableLiveData()
    val getMovieTvShow: MutableLiveData<MovieTvShowDisplay> = MutableLiveData()

    fun searchMoviesTvShowsBar(searchQuery: String, currentDataSource: DataSource) =
        viewModelScope.launch {

            if (currentDataSource == DataSource.API) {
                val response = theMovieRepositoryImpl.searchMoviesTvShows(searchQuery).results
                val searchScreenDisplay = SearchScreenDisplay(dataSource = DataSource.API, response)
                searchMoviesTvShows.postValue(searchScreenDisplay)
            } else {
                dataFromDatabase.invoke(searchQuery).collectLatest { result ->
                    val searchScreenDisplay = SearchScreenDisplay(DataSource.LOCAL, result)
                    searchMoviesTvShows.postValue(searchScreenDisplay)
                }
            }

        }

    fun changeDataSource(currentDataSource: DataSource) = viewModelScope.launch {
        if (currentDataSource == DataSource.API) {
            dataFromDatabase.invoke("").collectLatest { result ->
                val searchScreenDisplay = SearchScreenDisplay(DataSource.LOCAL, result)
                searchMoviesTvShows.postValue(searchScreenDisplay)
            }
        } else {
            val searchScreenDisplay = SearchScreenDisplay(DataSource.API, listOf())
            searchMoviesTvShows.postValue(searchScreenDisplay)
        }
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