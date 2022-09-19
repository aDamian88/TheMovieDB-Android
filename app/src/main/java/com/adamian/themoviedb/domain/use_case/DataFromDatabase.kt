package com.adamian.themoviedb.domain.use_case

import com.adamian.themoviedb.data.local.model.MovieTvShowEntity
import com.adamian.themoviedb.data.network.model.MovieTvShow
import com.adamian.themoviedb.domain.repository.TheMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataFromDatabase @Inject constructor(
    private val repository: TheMovieRepository
) {

    operator fun invoke(query: String): Flow<List<MovieTvShow>> = flow {
        var movieTvShowDisplayList = listOf<MovieTvShow>()
        if (query.isEmpty()) {
            val movieTvShowStoredList: List<MovieTvShowEntity> = repository.getAllStoredData()
            movieTvShowStoredList.forEach { item ->
                movieTvShowDisplayList += item.toMovieTvShow()
            }
        } else {
            val movieTvShowStoredList: List<MovieTvShowEntity> =
                repository.searchFromDatabase(query)
            movieTvShowStoredList.forEach { item ->
                movieTvShowDisplayList += item.toMovieTvShow()
            }
        }

        emit(movieTvShowDisplayList)
    }

    private fun MovieTvShowEntity.toMovieTvShow(): MovieTvShow {
        return MovieTvShow(
            id = id,
            title = title,
            name = title,
            mediaType = mediaType,
            posterPath = posterPath,
            releaseDate = releaseDate,
            firstAirDate = releaseDate
        )
    }
}