package com.adamian.themoviedb.ui

import androidx.lifecycle.ViewModel
import com.adamian.themoviedb.data.repository.TheMovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TheMovieViewModel @Inject constructor(
    private val theMovieRepositoryImpl: TheMovieRepositoryImpl
): ViewModel() {
}