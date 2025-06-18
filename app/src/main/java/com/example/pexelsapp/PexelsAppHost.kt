package com.example.pexelsapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pexelsapp.screens.bookmarks.BookmarksScreen
import com.example.pexelsapp.screens.ditails.DetailsScreen
import com.example.pexelsapp.screens.home.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun PexelsAppHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                modifier,
                onNavToDetailsScreen = {
                    navController.navigate(Details)
                },
                onNavToBookmarksScreen = {
                    navController.navigate(Bookmarks)
                }
            )
        }

        composable<Bookmarks> {
            BookmarksScreen(
                modifier,
                onNavToDetailsScreen = {
                    navController.navigate(Details)
                },
                onNavToHomeScreen = {
                    navController.navigate(Home)
                }
            )
        }

        composable<Details> {
            DetailsScreen(modifier)
        }
    }
}

@Serializable
object Home

@Serializable
object Bookmarks

@Serializable
object Details