package com.adamian.themoviedb.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val id: Int,
    val genres: List<Genre>,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
)