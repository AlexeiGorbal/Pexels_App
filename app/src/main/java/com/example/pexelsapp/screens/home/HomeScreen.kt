package com.example.pexelsapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pexelsapp.screens.home.elements.FeaturedCollectionsList
import com.example.pexelsapp.screens.home.elements.ListPhotos
import com.example.pexelsapp.screens.home.elements.ListPlaceholders
import com.example.pexelsapp.screens.home.elements.NoNetworkState
import com.example.pexelsapp.screens.home.elements.NoResultFoundState
import com.example.pexelsapp.screens.home.elements.ProgressBarView
import com.example.pexelsapp.screens.home.elements.SearchField

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavToDetailsScreen: (Long) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val state by viewModel.state.collectAsState(null)
    val text by viewModel.searchValue.collectAsState()

    Column(modifier) {
        SearchField(
            searchValue = text ?: "",
            onSearchChange = { viewModel.search(it) },
            onClearClick = { viewModel.search("") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 12.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Color.LightGray)
        )

        when (uiState) {
            UiState.Error -> {
                ErrorState(
                    onTryAgainClick = { viewModel.tryAgain() }
                )
            }

            UiState.Loading -> {
                LoadingState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                )
            }

            UiState.Founding -> {
                state?.let { state ->
                    FoundingState(
                        state = state,
                        onSearchValue = { viewModel.search(it) },
                    )
                }
            }

            UiState.NotFound -> {
                state?.let { state ->
                    NotFoundState(
                        state = state,
                        onSearchValue = { viewModel.search(it) },
                        onExploreClick = { viewModel.onExplore() }
                    )
                }
            }

            is UiState.Loaded -> {
                state?.let { state ->
                    LoadedState(
                        state = state,
                        onSearchValue = { viewModel.search(it) },
                        onNavToDetailsScreen = { onNavToDetailsScreen(it) })
                }
            }
        }
    }
}

@Composable
fun NotFoundState(
    state: HomeScreenState,
    onSearchValue: (String) -> Unit,
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    state.collections.also {
        FeaturedCollectionsList(
            collectionsList = it,
            onClick = { collectionTitle ->
                onSearchValue(collectionTitle)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp)
        )
    }
    NoResultFoundState(
        onExploreClick = onExploreClick,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    ProgressBarView()
    ListPlaceholders(modifier = modifier)
}

@Composable
fun ErrorState(
    onTryAgainClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NoNetworkState(onTryAgainClick = onTryAgainClick)
}

@Composable
fun LoadedState(
    state: HomeScreenState,
    onSearchValue: (String) -> Unit,
    onNavToDetailsScreen: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    state.collections.also { collectionsList ->
        FeaturedCollectionsList(
            collectionsList = collectionsList,
            onClick = { collectionTitle ->
                onSearchValue(collectionTitle)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp)
        )
    }
    state.photos.also { photos ->
        ListPhotos(
            listPhotos = photos,
            onClick = { onNavToDetailsScreen(it) },
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
        )
    }
}

@Composable
fun FoundingState(
    state: HomeScreenState,
    onSearchValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    state.collections.also {
        FeaturedCollectionsList(
            collectionsList = it,
            onClick = { collectionTitle ->
                onSearchValue(collectionTitle)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp)
        )
    }
    ProgressBarView()
}