package com.adamian.themoviedb.domain.repository

import com.adamian.themoviedb.data.network.model.MultiMovieResponse

interface TheMovieRepository {
    suspend fun searchMoviesTvShows(searchQuery: String): MultiMovieResponse
}