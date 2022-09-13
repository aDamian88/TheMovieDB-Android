package com.adamian.themoviedb.data.repository

import android.app.Application
import com.adamian.themoviedb.data.network.TheMovieAPI
import com.adamian.themoviedb.domain.repository.TheMovieRepository
import javax.inject.Inject

class TheMovieRepositoryImpl @Inject constructor(
    private val theMovieAPI: TheMovieAPI
): TheMovieRepository {
}