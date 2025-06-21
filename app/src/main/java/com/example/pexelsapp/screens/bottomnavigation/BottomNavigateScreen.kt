package com.example.pexelsapp.screens.bottomnavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pexelsapp.Bookmarks
import com.example.pexelsapp.Home
import com.example.pexelsapp.PexelsAppHost
import com.example.pexelsapp.R

@Composable
fun BottomNavigateScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            val homeRoute = Home::class.java.name
            val bookmarksRoute = Bookmarks::class.java.name

            val showBottomBar = currentRoute == homeRoute || currentRoute == bookmarksRoute
            if (showBottomBar) {
                BottomNavBar(navController)
            }
        }
    ) { innerPadding ->
        PexelsAppHost(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var selectedDestination by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(modifier = modifier) {
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

val items = listOf(
    Item("home", R.drawable.ic_selected_home, R.drawable.ic_not_selected_home),
    Item("book", R.drawable.ic_selected_bookmark, R.drawable.ic_not_selected_bookmark)
)

data class Item(
    val title: String,
    val selectedIcon: Int,
    val notSelectedIcon: Int,
)