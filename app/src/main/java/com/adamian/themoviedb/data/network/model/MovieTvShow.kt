package com.adamian.themoviedb.data.network.model

import com.google.gson.annotations.SerializedName

class MovieTvShow(
    val id: Int,
    val title: String,
    val name: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("first_air_date")
    val firstAirDate: String,
)