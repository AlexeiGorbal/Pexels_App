package com.example.pexelsapp.screens.home

import androidx.compose.ui.graphics.Color
import com.example.pexelsapp.domain.Collection
import com.example.pexelsapp.domain.Photo

data class HomeScreenState(
    val photos: List<Photo>,
    val collections: List<CollectionItem>,
)

data class CollectionItem(
    val title: String,
    val color: Color = Color.LightGray,
)