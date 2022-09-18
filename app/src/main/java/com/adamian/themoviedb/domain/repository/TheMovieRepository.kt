package com.adamian.themoviedb.domain.repository

import com.adamian.themoviedb.data.network.model.MovieDetailsResponse
import com.adamian.themoviedb.data.network.model.MovieTvShowVideoResponse
import com.adamian.themoviedb.data.network.model.MultiMovieResponse
import com.adamian.themoviedb.data.network.model.TvShowDetailsResponse

interface TheMovieRepository {

    suspend fun searchMoviesTvShows(searchQuery: String): MultiMovieResponse

    suspend fun getMovieDetails(movieId: String): MovieDetailsResponse

    suspend fun getTvShowDetails(tvShowId: String): TvShowDetailsResponse

    suspend fun getMovieVideo(movieId: String): MovieTvShowVideoResponse

    suspend fun getTvShowVideo(movieId: String): MovieTvShowVideoResponse
}