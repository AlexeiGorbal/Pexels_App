package com.example.pexelsapp.screens.bookmarks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R

@Composable
fun BookmarksScreen(
    modifier: Modifier = Modifier,
    onNavToDetailsScreen: (Long) -> Unit,
    onNavToHomeScreen: () -> Unit,
) {
    Column(modifier) {
        Title()
        ListBookmarks(
            listOf(
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
            ),
            { onNavToDetailsScreen(0) })
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Text("hfjds")
}

@Composable
fun ListBookmarks(
    listItems: List<Int>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(listItems) { photo ->
                Image(
                    painter = painterResource(R.drawable.ic_placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable { onClick() }
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}