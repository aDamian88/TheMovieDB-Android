package com.adamian.themoviedb.domain.use_case

import com.adamian.themoviedb.data.network.model.TvShowDetailsResponse
import com.adamian.themoviedb.domain.model.MovieTvShowDisplay
import com.adamian.themoviedb.domain.repository.TheMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowMapper @Inject constructor(
    private val repository: TheMovieRepository
) {

    operator fun invoke(tvShowId: String): Flow<MovieTvShowDisplay> = flow {
        val tvShowDetailsResponse = repository.getTvShowDetails(tvShowId)
        var youtubeKey = ""
        val tvShowVideoList = repository.getTvShowVideo(tvShowDetailsResponse.id.toString())
        tvShowVideoList.results.forEach {
            if (it.type == "Trailer") {
                youtubeKey = it.key
            }
        }
        emit(tvShowDetailsResponse.toMovieTvShowDisplay(youtubeKey, true))
    }

    private fun TvShowDetailsResponse.toMovieTvShowDisplay(
        youtubeKey: String,
        isLocalStored: Boolean
    ): MovieTvShowDisplay {
        return MovieTvShowDisplay(
            id = id,
            genre = if (genres.isEmpty()) "" else genres[0].name,
            overview = overview,
            posterPath = posterPath,
            title = name,
            trailerKey = youtubeKey,
            isStored = isLocalStored
        )
    }

}