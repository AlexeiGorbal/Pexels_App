package com.example.pexelsapp.web

import android.content.Context
import com.example.pexelsapp.domain.remote.PexelsAppApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideFilmApi(retrofit: Retrofit): PexelsAppApi {
        return retrofit.create(PexelsAppApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext context:Context): Retrofit {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .client(
                OkHttpClient.Builder()
                    .cache(myCache)
                    .addInterceptor(ApiKeyInterceptor())
                    .addInterceptor(logger)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}