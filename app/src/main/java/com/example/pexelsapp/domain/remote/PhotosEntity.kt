package com.example.pexelsapp.domain.remote

import com.google.gson.annotations.SerializedName

data class PhotosEntity(
    @SerializedName("photos") val photos: List<PhotoEntity>
)

data class PhotoEntity(
    @SerializedName("id") val id: Long,
    @SerializedName("photographer") val photographer: String,
    @SerializedName("src") val src: SrcEntity
)

data class SrcEntity(
    @SerializedName("original") val original: String
)