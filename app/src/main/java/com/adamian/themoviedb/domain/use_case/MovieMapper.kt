package com.adamian.themoviedb.domain.use_case

import com.adamian.themoviedb.data.network.model.MovieDetailsResponse
import com.adamian.themoviedb.domain.model.MovieTvShowDisplay
import com.adamian.themoviedb.domain.repository.TheMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieMapper @Inject constructor(
    private val repository: TheMovieRepository
) {

    operator fun invoke(movieId: String): Flow<MovieTvShowDisplay> = flow {
        val movieDetailsResponse = repository.getMovieDetails(movieId)
        var youtubeKey = ""
        val movieVideoList = repository.getMovieVideo(movieDetailsResponse.id.toString())
        movieVideoList.results.forEach {
            if (it.type == "Trailer") {
                youtubeKey = it.key
            }
        }
        emit(movieDetailsResponse.toMovieTvShowDisplay(youtubeKey, true))
    }

    private fun MovieDetailsResponse.toMovieTvShowDisplay(
        youtubeKey: String,
        isLocalStored: Boolean
    ): MovieTvShowDisplay {
        return MovieTvShowDisplay(
            id = id,
            genre = if (genres.isEmpty()) "" else genres[0].name,
            overview = overview,
            posterPath = posterPath,
            title = title,
            trailerKey = youtubeKey,
            isStored = isLocalStored
        )
    }

}