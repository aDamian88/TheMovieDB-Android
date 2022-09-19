package com.adamian.themoviedb.data.local

import androidx.room.*
import com.adamian.themoviedb.data.local.model.MovieTvShowEntity

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTvShow(
        movieTvShowEntity: MovieTvShowEntity
    )

    @Delete
    suspend fun deleteMovieTvShow(
        movieTvShowEntity: MovieTvShowEntity
    )

    @Query("SELECT * FROM MovieTvShowEntity")
    suspend fun getAllMovieTvShows(): List<MovieTvShowEntity>

    @Query("SELECT * FROM MovieTvShowEntity WHERE title LIKE :query ")
    suspend fun searchMovieTvShows(query: String): List<MovieTvShowEntity>

    @Query("SELECT EXISTS(SELECT * FROM MovieTvShowEntity WHERE id = :id AND mediaType = :type)")
    suspend fun isEntityExist(id: Int, type: String): Boolean

    @Query("DELETE FROM MovieTvShowEntity WHERE id = :id AND mediaType = :type")
    suspend fun deleteEntityByIdAndType(id: Int,type: String)

}