package com.example.pexelsapp.domain

import com.google.gson.annotations.SerializedName

data class PhotosEntity(
    @SerializedName("photos") val photos: List<PhotoEntity>
)

data class PhotoEntity(
    @SerializedName("id") val id: Long,
    @SerializedName("src") val src: SrcEntity
)

data class SrcEntity(
    @SerializedName("original") val original: String
)