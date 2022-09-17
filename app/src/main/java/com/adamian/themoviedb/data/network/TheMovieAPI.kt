package com.adamian.themoviedb.data.network

import com.adamian.themoviedb.data.network.model.MultiMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieAPI {

    @GET("search/multi")
    suspend fun searchMoviesTvShows(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: String
    ): MultiMovieResponse

}