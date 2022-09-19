package com.adamian.themoviedb.domain.model

import com.adamian.themoviedb.data.network.model.MovieTvShow
import com.adamian.themoviedb.utils.DataSource

class SearchScreenDisplay(
    val dataSource: DataSource,
    val results: List<MovieTvShow>
) {
}