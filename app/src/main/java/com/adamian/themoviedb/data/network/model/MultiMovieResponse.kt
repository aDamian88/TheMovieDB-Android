package com.adamian.themoviedb.data.network.model

import com.google.gson.annotations.SerializedName

class MultiMovieResponse(
    val page: Int,
    val results: List<MovieTvShow>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)