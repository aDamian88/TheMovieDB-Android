package com.adamian.themoviedb.utils

object Constants {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val AUTH_KEY = "6b2e856adafcc7be98bdf0d8b076851c"
    const val SEARCH_DELAY: Long = 1000
    const val MIN_SEARCH_CHARACTERS = 2
    const val QUERY_PAGE_SIZE = 20
    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="

    fun getPosterPath(posterPath: String?): String {
        return BASE_POSTER_PATH + posterPath
    }

    fun getYoutubeVideoPath(videoPath: String): String {
        return YOUTUBE_VIDEO_URL + videoPath
    }

}