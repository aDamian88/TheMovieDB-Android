package com.adamian.themoviedb.data.network.model

data class MovieTvShowVideoResponse(
    val id: Int,
    val results: List<MovieTvShowVideo>
)