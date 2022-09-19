package com.adamian.themoviedb.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.adamian.themoviedb.data.local.AppDao
import com.adamian.themoviedb.data.local.ApplicationDatabase
import com.adamian.themoviedb.data.network.TheMovieAPI
import com.adamian.themoviedb.data.repository.TheMovieRepositoryImpl
import com.adamian.themoviedb.domain.repository.TheMovieRepository
import com.adamian.themoviedb.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationDatabase(app: Application): ApplicationDatabase {
        return Room.databaseBuilder(
            app,
            ApplicationDatabase::class.java,
            "the_movie_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideApplicationDao(
        database: ApplicationDatabase
    ): AppDao {
        return database.appDao()
    }

    @Provides
    @Singleton
    fun provideTheMovieAPI(): TheMovieAPI {
        return Retrofit.Builder()
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TheMovieAPI::class.java)
    }

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideTheMovieRepository(theMovieAPI: TheMovieAPI, appDao: AppDao): TheMovieRepository {
        return TheMovieRepositoryImpl(theMovieAPI, appDao)
    }
}