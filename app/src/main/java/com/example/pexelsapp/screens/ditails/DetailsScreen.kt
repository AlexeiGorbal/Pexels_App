package com.example.pexelsapp.screens.ditails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.pexelsapp.R

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    photoId: Long,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {

    val photo by viewModel.photo.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getPhotoById(photoId)
    }

    Column(modifier) {
        Row {
            BackButton({})
            Title("ghfjdks")
        }
        photo?.also {
            SelectedPhoto(it.photo)
        }
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
fun SelectedPhoto(
    photo: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = photo,
        contentDescription = null,
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(16.dp))
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