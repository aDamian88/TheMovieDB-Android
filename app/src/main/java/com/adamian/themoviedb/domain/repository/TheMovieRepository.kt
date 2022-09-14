package com.adamian.themoviedb.domain.repository

import com.adamian.themoviedb.data.network.model.MultiMovieResponse
import retrofit2.Response

interface TheMovieRepository {
    suspend fun searchMoviesTvShows(searchQuery: String): MultiMovieResponse
}