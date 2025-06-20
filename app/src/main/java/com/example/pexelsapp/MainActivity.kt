package com.example.pexelsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                            NavigationBar() {
                                items.forEachIndexed { index, item ->


                                    this@NavigationBar.NavigationBarItem(
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
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                if (selectedDestination == index) {
                                                    Divider(
                                                        color = colorResource(id = R.color.purple_200),
                                                        modifier = Modifier
                                                            .size(width = 24.dp, height = 2.dp)
                                                            .align(Alignment.CenterHorizontally)
                                                    )
                                                }

                                                if (selectedDestination == index) {
                                                    Icon(
                                                        painter = painterResource(item.selectedIcon),
                                                        contentDescription = null,
                                                        modifier = Modifier.size(24.dp)
                                                    )
                                                } else {
                                                    Icon(
                                                        painter = painterResource(item.notSelectedIcon),
                                                        contentDescription = null,
                                                        modifier = Modifier.size(24.dp)
                                                    )
                                                }
                                            }
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
    Item("home", R.drawable.selected_home_ic, R.drawable.not_selected_home_ic),
    Item("book", R.drawable.selected_bookmark_ic, R.drawable.not_selected_bookmark_ic)
)

data class Item(
    val title: String,
    val selectedIcon: Int,
    val notSelectedIcon: Int,
)