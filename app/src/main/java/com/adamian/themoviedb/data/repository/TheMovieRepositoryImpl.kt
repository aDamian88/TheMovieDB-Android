package com.adamian.themoviedb.data.repository

import android.app.Application
import com.adamian.themoviedb.data.network.TheMovieAPI
import com.adamian.themoviedb.data.network.model.MultiMovieResponse
import com.adamian.themoviedb.domain.repository.TheMovieRepository
import com.adamian.themoviedb.utils.Constants.AUTH_KEY
import retrofit2.Response
import javax.inject.Inject

class TheMovieRepositoryImpl @Inject constructor(
    private val theMovieAPI: TheMovieAPI
) : TheMovieRepository {
    override suspend fun searchMoviesTvShows(searchQuery: String): MultiMovieResponse {
        return theMovieAPI.searchMoviesTvShows(AUTH_KEY, searchQuery, "1")
    }
}