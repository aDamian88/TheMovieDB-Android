package com.adamian.themoviedb.data.repository

import com.adamian.themoviedb.data.local.AppDao
import com.adamian.themoviedb.data.local.model.MovieTvShowEntity
import com.adamian.themoviedb.data.network.TheMovieAPI
import com.adamian.themoviedb.data.network.model.MovieDetailsResponse
import com.adamian.themoviedb.data.network.model.MovieTvShowVideoResponse
import com.adamian.themoviedb.data.network.model.MultiMovieResponse
import com.adamian.themoviedb.data.network.model.TvShowDetailsResponse
import com.adamian.themoviedb.domain.repository.TheMovieRepository
import com.adamian.themoviedb.utils.Constants.AUTH_KEY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TheMovieRepositoryImpl @Inject constructor(
    private val theMovieAPI: TheMovieAPI,
    private val dao: AppDao
) : TheMovieRepository {

    override suspend fun searchMoviesTvShows(searchQuery: String): MultiMovieResponse {
        return theMovieAPI.searchMoviesTvShows(AUTH_KEY, searchQuery, "1")
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailsResponse {
        return theMovieAPI.getMovieDetails(movieId,AUTH_KEY)
    }

    override suspend fun getTvShowDetails(tvShowId: String): TvShowDetailsResponse {
        return theMovieAPI.getTvShowDetails(tvShowId, AUTH_KEY)
    }

    override suspend fun getMovieVideo(movieId: String): MovieTvShowVideoResponse {
        return theMovieAPI.getMovieVideos(movieId, AUTH_KEY)
    }

    override suspend fun getTvShowVideo(tvShowId: String): MovieTvShowVideoResponse {
        return theMovieAPI.getTvShowVideos(tvShowId, AUTH_KEY)
    }

    override suspend fun saveMovieTvShowEntity(movieTvShowEntity: MovieTvShowEntity) {
        dao.insertMovieTvShow(movieTvShowEntity)
    }

    override suspend fun isEntityExistsOnDatabase(id: Int, type: String): Boolean {
        return dao.isEntityExist(id,type)
    }

    override suspend fun deleteEntity(id: Int, type: String) {
        dao.deleteEntityByIdAndType(id,type)
    }

}