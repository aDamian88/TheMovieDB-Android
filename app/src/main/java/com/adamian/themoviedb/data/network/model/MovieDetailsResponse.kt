package com.adamian.themoviedb.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val id: Int,
    val genres: List<Genre>,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String
)