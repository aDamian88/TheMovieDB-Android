package com.adamian.themoviedb.domain.use_case

import com.adamian.themoviedb.data.local.model.MovieTvShowEntity
import com.adamian.themoviedb.domain.model.MovieTvShowDisplay
import com.adamian.themoviedb.domain.repository.TheMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveDeleteMovieTvShow @Inject constructor(
    private val repository: TheMovieRepository
) {

    operator fun invoke(
        movieTvShowEntity: MovieTvShowEntity
    ): Flow<MovieTvShowDisplay> = flow {
        val isLocalStored = repository.isEntityExistsOnDatabase(movieTvShowEntity.id, movieTvShowEntity.mediaType)
        if (isLocalStored) {
            repository.deleteEntity(movieTvShowEntity.id, movieTvShowEntity.mediaType)
        } else {
            repository.saveMovieTvShowEntity(movieTvShowEntity)
        }
        emit(movieTvShowEntity.toMovieTvShowDisplay(!isLocalStored))
    }

    private fun MovieTvShowEntity.toMovieTvShowDisplay(
        isLocalStored: Boolean
    ): MovieTvShowDisplay{
        return MovieTvShowDisplay(
            id = id,
            title = title,
            posterPath = posterPath,
            genre = genre,
            overview = overview,
            releaseDate = releaseDate,
            trailerKey = trailerKey,
            isStored = isLocalStored,
        )
    }
}