package com.adamian.themoviedb.data.network.model

data class MovieVideoResponse(
    val id: Int,
    val results: List<MovieVideo>
)