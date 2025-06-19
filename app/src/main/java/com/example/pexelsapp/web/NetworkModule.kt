package com.example.pexelsapp.web

import com.example.pexelsapp.domain.PexelsAppApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideRetrofit(): Retrofit {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(ApiKeyInterceptor())
                    .addInterceptor(logger)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}