package com.adamian.themoviedb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MovieTvShowEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val mediaType: String,
    val posterPath: String?,
    val releaseDate: String,
    val genre: String,
    val overview: String,
    val trailerKey: String
)
