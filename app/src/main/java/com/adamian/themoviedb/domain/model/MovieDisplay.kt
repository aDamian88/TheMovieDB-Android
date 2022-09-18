package com.adamian.themoviedb.domain.model

class MovieDisplay(
    val id: Int,
    val genre: String,
    val overview: String,
    val posterPath: String,
    val title: String,
    val trailerKey: String,
    val isStored: Boolean
) {
}