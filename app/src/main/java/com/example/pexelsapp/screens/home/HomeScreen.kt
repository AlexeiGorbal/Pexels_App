package com.example.pexelsapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.pexelsapp.R
import com.example.pexelsapp.domain.Collection
import com.example.pexelsapp.domain.Photo

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavToDetailsScreen: (Long) -> Unit,
) {
    val photos by viewModel.photos.collectAsState(null)
    val collection by viewModel.collections.collectAsState(null)
    val text by viewModel.searchValue.collectAsState()

    Column(modifier) {
        SearchField(text ?: "", { viewModel.onSearchValue(it) }, { viewModel.onSearchValue("") })
        collection?.also {
            FeaturedCollectionsList(
                it,
                { collectionTitle ->
                    viewModel.onSearchValue(collectionTitle)
                },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp)
            )
        }
        photos?.also { photos ->
            ListPhotos(photos, { onNavToDetailsScreen(it) }, Modifier.fillMaxSize())
        }
    }
}

@Composable
fun SearchField(
    searchValue: String,
    onSearchChange: (String) -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 12.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(Color.LightGray)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.search_ic),
                contentDescription = null,
                modifier = Modifier.padding(start = 20.dp)
            )

            TextField(
                value = searchValue,
                onValueChange = { onSearchChange(it) },
                placeholder = { Text("Search") },
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),

            )

            IconButton(
                onClick = onClearClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.clear_ic),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun FeaturedCollectionsList(
    collectionsList: List<Collection>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        items(collectionsList) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color.LightGray)
                    .clickable { onClick(it.title) }
            ) {
                Text(
                    text = it.title,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}

@Composable
fun ListPhotos(
    listPhotos: List<Photo>,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(24.dp),
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        verticalItemSpacing = 15.dp,
        modifier = modifier
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