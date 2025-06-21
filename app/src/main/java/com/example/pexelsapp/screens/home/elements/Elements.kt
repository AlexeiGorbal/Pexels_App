package com.example.pexelsapp.screens.home.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.pexelsapp.R
import com.example.pexelsapp.domain.Photo
import com.example.pexelsapp.screens.home.CollectionItem

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
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
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
                onClick = {
                    onClearClick()
                    focusManager.clearFocus()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_clear),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ProgressBarView(modifier: Modifier = Modifier) {
    LinearProgressIndicator(
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .padding(top = 12.dp)
            .height(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
    )
}

@Composable
fun FeaturedCollectionsList(
    collectionsList: List<CollectionItem>,
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
                    .background(it.color)
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
        contentPadding = PaddingValues(bottom = 24.dp),
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
fun ListPlaceholders(
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(bottom = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        verticalItemSpacing = 15.dp,
        modifier = modifier
    ) {
        items(listOf(4)) {
            PlaceholderItem()
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

@Composable
fun PlaceholderItem(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width = 155.dp, height = 170.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.DarkGray)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_placeholder),
            contentDescription = null,
        )
    }
}

@Composable
fun NoResultFoundState(
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "No results found",
            fontSize = 14.sp,
        )
        TextButton(
            onClick = onExploreClick
        ) {
            Text(
                text = "Explore",
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
fun NoNetworkState(
    onTryAgainClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.ic_no_network),
            contentDescription = null
        )
        TextButton(
            onClick = onTryAgainClick
        ) {
            Text(
                text = "Try Again",
                fontSize = 18.sp,
            )
        }
    }
}