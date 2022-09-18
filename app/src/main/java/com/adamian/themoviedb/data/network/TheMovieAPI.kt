package com.adamian.themoviedb.data.network

import com.adamian.themoviedb.data.network.model.MovieDetailsResponse
import com.adamian.themoviedb.data.network.model.MovieTvShowVideoResponse
import com.adamian.themoviedb.data.network.model.MultiMovieResponse
import com.adamian.themoviedb.data.network.model.TvShowDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieAPI {

    @GET("search/multi")
    suspend fun searchMoviesTvShows(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: String
    ): MultiMovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String,
    ): MovieDetailsResponse

    @GET("movie/{movieId}/videos")
    suspend fun getMovieVideos(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String,
    ): MovieTvShowVideoResponse

    @GET("tv/{tvShowId}")
    suspend fun getTvShowDetails(
        @Path("tvShowId") tvShowId: String,
        @Query("api_key") apiKey: String,
    ): TvShowDetailsResponse

    @GET("tv/{tvShowId}/videos")
    suspend fun getTvShowVideos(
        @Path("tvShowId") tvShowId: String,
        @Query("api_key") apiKey: String,
    ): MovieTvShowVideoResponse
}