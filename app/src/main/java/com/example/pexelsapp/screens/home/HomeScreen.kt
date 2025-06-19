package com.example.pexelsapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.pexelsapp.domain.Photo

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavToDetailsScreen: (Long) -> Unit,
    onNavToBookmarksScreen: (Int) -> Unit,
) {
    val photos by viewModel.photos.collectAsState(null)
    val text by viewModel.searchValue.collectAsState()

    Column(modifier) {
        SearchField(text ?: "", { viewModel.onSearchValue(it) })
        photos?.also { photos ->
            ListPhotos(photos, { onNavToDetailsScreen(it) })
        }
    }
}

@Composable
fun SearchField(
    searchValue: String,
    onSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = searchValue,
        onValueChange = { onSearchChange(it) },
    )
}

@Composable
fun ListPhotos(
    listPhotos: List<Photo>,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp,
        modifier = Modifier.fillMaxSize()
    ) {
        items(listPhotos) { photo ->
            PhotoItem(photo, { onClick(photo.id) })
        }
    }
}

@Composable
fun PhotoItem(
    photo: Photo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = photo.photo,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.DarkGray)
            .clickable { onClick() }
    )
}