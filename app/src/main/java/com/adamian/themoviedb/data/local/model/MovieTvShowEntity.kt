package com.adamian.themoviedb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieTvShowEntity(
    @PrimaryKey
    val id: Int,
    val remoteId: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    val releaseDate: String,
)
