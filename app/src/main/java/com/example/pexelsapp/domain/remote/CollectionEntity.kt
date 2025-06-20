package com.example.pexelsapp.domain.remote

import com.google.gson.annotations.SerializedName

data class CollectionsEntity(@SerializedName("collections") val collections: List<CollectionEntity>)

data class CollectionEntity(@SerializedName("title") val title: String)