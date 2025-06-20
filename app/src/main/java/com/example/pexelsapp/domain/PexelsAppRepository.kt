package com.example.pexelsapp.domain

import com.example.pexelsapp.domain.remote.CollectionEntity
import com.example.pexelsapp.domain.remote.PexelsAppApi
import com.example.pexelsapp.domain.remote.PhotoEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PexelsAppRepository @Inject constructor(
    private val api: PexelsAppApi
) {

    suspend fun getFeaturedCollections(): List<Collection> {
        return api.getFeaturedCollections().collections.map { it.toModel() }
    }

    suspend fun getPhotos(): List<Photo> {
        return api.getPhotos().photos.map { it.toModel() }
    }

    suspend fun getPhotoById(photoId: Long): Photo {
        return api.getPhotoById(photoId).toModel()
    }

    suspend fun getSearchPhotos(inputText: String): List<Photo> {
        return api.getSearchPhotos(inputText).photos.map { it.toModel() }
    }
}

fun PhotoEntity.toModel(): Photo {
    return Photo(id, photographer, src.original)
}

fun CollectionEntity.toModel(): Collection {
    return Collection(title)
}