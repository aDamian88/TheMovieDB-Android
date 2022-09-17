package com.adamian.themoviedb.data.network.model

import com.google.gson.annotations.SerializedName

class MovieTvShow(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("release_date")
    val releaseDate: String,
)