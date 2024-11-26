package com.gopaddi.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInVertically
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gopaddi.app.ui.screens.DateScreen
import com.gopaddi.app.ui.screens.Screen1
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
        }, exitTransition = {
            slideOutVertically(targetOffsetY = { -it })
        }, popEnterTransition = {
            slideInVertically(initialOffsetY = { it })
        }, popExitTransition = {
            slideOutVertically(targetOffsetY = { -it })
        }) {
            WhereToScreen(navController = navController)
        }
        composable(route = "DateScreen", enterTransition = {
            slideInVertically(initialOffsetY = { it })
        }, exitTransition = {
            slideOutVertically(targetOffsetY = { -it })
        }, popEnterTransition = {
            slideInVertically(initialOffsetY = { it })
        }, popExitTransition = {
            slideOutVertically(targetOffsetY = { -it })
        }) {
            DateScreen(navController = navController)
        }
    }
}