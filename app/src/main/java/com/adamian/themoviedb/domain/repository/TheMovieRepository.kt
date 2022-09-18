package com.adamian.themoviedb.domain.repository

import com.adamian.themoviedb.data.network.model.MovieDetailsResponse
import com.adamian.themoviedb.data.network.model.MovieVideoResponse
import com.adamian.themoviedb.data.network.model.MultiMovieResponse

interface TheMovieRepository {

    suspend fun searchMoviesTvShows(searchQuery: String): MultiMovieResponse

    suspend fun getMovieDetails(movieId: String): MovieDetailsResponse

    suspend fun getMovieVideo(movieId: String): MovieVideoResponse
}