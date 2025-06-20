package com.example.pexelsapp.screens.ditails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.pexelsapp.R

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    photoId: Long,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onBackPress: () -> Unit
) {
    val photo by viewModel.photo.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getPhotoById(photoId)
    }
    photo?.also {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 37.dp)
            ) {
                BackButton({ onBackPress() })
                Title(it.photographer, Modifier.weight(1f))
            }

            SelectedPhoto(it.photo, Modifier.padding(start = 24.dp, end = 24.dp, top = 29.dp))
            Spacer(Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            ) {
                DownloadButton({})
                BookmarkButton({})
            }
        }
    }
}

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_back_bt),
            contentDescription = null,
        )
    }
}

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = modifier
    )
}

@Composable
fun SelectedPhoto(
    photo: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = photo,
        contentDescription = null,
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun DownloadButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(height = 48.dp, width = 180.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.LightGray)
            .clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Red),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_download),
                contentDescription = null
            )
        }

        Text(
            text = "Download",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}


@Composable
fun BookmarkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(48.dp)
            .background(Color.LightGray, CircleShape)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_not_selected_bookmark),
            contentDescription = null,
        )
    }
}