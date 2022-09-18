package com.adamian.themoviedb.data.repository

import com.adamian.themoviedb.data.network.TheMovieAPI
import com.adamian.themoviedb.data.network.model.MovieDetailsResponse
import com.adamian.themoviedb.data.network.model.MovieVideoResponse
import com.adamian.themoviedb.data.network.model.MultiMovieResponse
import com.adamian.themoviedb.domain.repository.TheMovieRepository
import com.adamian.themoviedb.utils.Constants.AUTH_KEY
import javax.inject.Inject

class TheMovieRepositoryImpl @Inject constructor(
    private val theMovieAPI: TheMovieAPI
) : TheMovieRepository {

    override suspend fun searchMoviesTvShows(searchQuery: String): MultiMovieResponse {
        return theMovieAPI.searchMoviesTvShows(AUTH_KEY, searchQuery, "1")
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailsResponse {
        return theMovieAPI.getMovieDetails(movieId,AUTH_KEY)
    }

    override suspend fun getMovieVideo(movieId: String): MovieVideoResponse {
        return theMovieAPI.getMovieVideos(movieId, AUTH_KEY)
    }

}