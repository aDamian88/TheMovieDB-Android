package com.adamian.themoviedb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adamian.themoviedb.data.local.model.MovieTvShowEntity

@Database(
    entities = [MovieTvShowEntity::class],
    version = 1
)
abstract class ApplicationDatabase : RoomDatabase(){

    abstract fun appDao(): AppDao

}