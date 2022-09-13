package com.adamian.themoviedb.data.network.model

class MultiMovieResponse(
    val page: Int,
    val results: List<MovieTvShow>,
    val total_pages: Int,
    val total_results: Int
)