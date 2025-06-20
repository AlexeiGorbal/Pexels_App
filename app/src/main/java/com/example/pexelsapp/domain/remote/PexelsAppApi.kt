package com.example.pexelsapp.domain.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsAppApi {
    @GET("collections/featured?per_page=7")
    suspend fun getFeaturedCollections(): CollectionsEntity

    @GET("curated")
    suspend fun getPhotos(@Query("per_page") perPage: Int = 30): PhotosEntity

    @GET("photos/{photoId}")
    suspend fun getPhotoById(@Path("photoId") photoId: Long): PhotoEntity

    @GET("search")
    suspend fun getSearchPhotos(
        @Query("query") inputText: String,
        @Query("per_page") perPage: Int = 15
    ): PhotosEntity
}