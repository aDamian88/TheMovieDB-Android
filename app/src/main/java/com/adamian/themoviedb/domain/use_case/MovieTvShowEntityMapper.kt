package com.adamian.themoviedb.domain.use_case

import com.adamian.themoviedb.data.local.model.MovieTvShowEntity
import com.adamian.themoviedb.domain.model.MovieTvShowDisplay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieTvShowEntityMapper @Inject constructor() {

    operator fun invoke(
        movieTvShowDisplay: MovieTvShowDisplay,
        type: String
    ): Flow<MovieTvShowEntity> = flow {
        emit(movieTvShowDisplay.toMovieTvShowEntity(type))
    }

    private fun MovieTvShowDisplay.toMovieTvShowEntity(
        type: String
    ): MovieTvShowEntity {
        return MovieTvShowEntity(
            id = id,
            title = title,
            mediaType = type,
            posterPath = posterPath,
            releaseDate = releaseDate,
            genre = genre,
            overview = overview,
            trailerKey = trailerKey,
        )
    }

}