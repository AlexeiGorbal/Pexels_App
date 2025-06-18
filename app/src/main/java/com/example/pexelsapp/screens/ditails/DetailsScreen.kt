package com.example.pexelsapp.screens.ditails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R

@Composable
fun DetailsScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Row {
            BackButton({})
            Title("ghfjdks")
        }
        SelectedImage(R.drawable.vector)
        Row {
            DownloadButton()
            BookmarkButton()
        }
    }
}

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
    }
}

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(text)
}

@Composable
fun SelectedImage(
    image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .background(Color.DarkGray)
            .size(30.dp)
    )
}

@Composable
fun DownloadButton(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = null
    )
}

@Composable
fun BookmarkButton(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = null
    )
}