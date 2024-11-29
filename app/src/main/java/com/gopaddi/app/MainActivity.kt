package com.gopaddi.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.gopaddi.app.data.TripData
import com.gopaddi.app.ui.screens.DateScreen
import com.gopaddi.app.ui.screens.Screen1
import com.gopaddi.app.ui.screens.TripInfoScreen
import com.gopaddi.app.ui.screens.WhereToScreen
import com.gopaddi.app.ui.theme.GoPaddiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoPaddiTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "screen1") {
        composable(route = "screen1") {
            Screen1(navController = navController, viewModel = viewModel())
        }
        composable(route = "whereToScreen", enterTransition = {
            slideInVertically(initialOffsetY = { it })
        }, popEnterTransition = {
            slideInVertically(initialOffsetY = { it })
        }, exitTransition = {
            slideOutVertically(targetOffsetY = { it })
        }, popExitTransition = {
            slideOutVertically(targetOffsetY = { it })
        }) {
            WhereToScreen(navController = navController, viewModel = viewModel())
        }
        composable(route = "DateScreen", enterTransition = {
            slideInVertically(initialOffsetY = { it })
        }, popEnterTransition = {
            slideInVertically(initialOffsetY = { it })
        }, exitTransition = {
            slideOutVertically(targetOffsetY = { it })
        }, popExitTransition = {
            slideOutVertically(targetOffsetY = { it })
        }) {
            DateScreen(navController = navController)
        }
        composable(route = "TripInfoScreen/{tripDataJson}",
            arguments = listOf(navArgument("tripDataJson") {
                type = NavType.StringType
            }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, animationSpec = tween(durationMillis = 300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it }, animationSpec = tween(durationMillis = 300)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it }, animationSpec = tween(durationMillis = 300)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it }, animationSpec = tween(durationMillis = 300)
                )
            }) {
            val tripData = Gson().fromJson(
                it.arguments?.getString("tripDataJson"), TripData::class.java
            )
            TripInfoScreen(navController = navController, tripData = tripData)
        }
    }
}