package com.example.pexelsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.pexelsapp.ui.theme.PexelsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PexelsAppTheme {
                val navController = rememberNavController()
                var selectedDestination by rememberSaveable { mutableIntStateOf(0) }
                var shouldHideBottomBar by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedVisibility(
                            visible = !shouldHideBottomBar,
                            enter = slideInVertically { 0 },
                            exit = slideOutVertically { 0 }
                        ) {
                            NavigationBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedDestination == index,
                                        onClick = {
                                            selectedDestination = index
                                            if (item.title == "home") {
                                                navController.navigate(Home)
                                            } else {
                                                navController.navigate(Bookmarks)
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                painter = painterResource(item.image),
                                                contentDescription = null
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    PexelsAppHost(navController, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

val items = listOf(
    Item("home", R.drawable.ic_launcher_foreground),
    Item("book", R.drawable.ic_launcher_foreground)
)

data class Item(
    val title: String,
    val image: Int
)