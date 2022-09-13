package com.adamian.themoviedb.di

import com.adamian.themoviedb.data.network.TheMovieAPI
import com.adamian.themoviedb.data.repository.TheMovieRepositoryImpl
import com.adamian.themoviedb.domain.repository.TheMovieRepository
import com.adamian.themoviedb.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideTheMovieRepository(theMovieAPI: TheMovieAPI): TheMovieRepository {
        return TheMovieRepositoryImpl(theMovieAPI)
    }
}