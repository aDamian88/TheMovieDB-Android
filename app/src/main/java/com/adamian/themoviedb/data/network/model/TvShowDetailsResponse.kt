package com.adamian.themoviedb.data.network.model

import com.google.gson.annotations.SerializedName

data class TvShowDetailsResponse(
    val id: Int,
    val genres: List<Genre>,
    val name: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
)