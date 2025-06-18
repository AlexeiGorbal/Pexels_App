package com.example.pexelsapp.screens.home

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pexelsapp.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavToDetailsScreen: () -> Unit,
    onNavToBookmarksScreen: (Int) -> Unit,
) {
    val text by viewModel.searchValue.collectAsState()

    Column(modifier) {
        SearchField(text ?: "", { viewModel.onSearchValue(it) })
        ListItems(
            listOf(
                R.drawable.vector,
                R.drawable.vector,
                R.drawable.vector,
                R.drawable.vector,
                R.drawable.vector,
                R.drawable.vector,
                R.drawable.vector,
                R.drawable.vector,
                R.drawable.vector,
                R.drawable.vector,
                R.drawable.vector,
                R.drawable.vector,
            ),
            { onNavToDetailsScreen() }
        )
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
fun ListItems(
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
                    painter = painterResource(R.drawable.vector),
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

@Composable
fun BottomNavigation(
    selectedDestination: Int,
    modifier: Modifier = Modifier
) {
}