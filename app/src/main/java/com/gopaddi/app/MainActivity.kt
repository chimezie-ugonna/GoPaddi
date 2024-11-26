package com.gopaddi.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gopaddi.app.ui.theme.GoPaddiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoPaddiTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = "", enterTransition = {
        slideInHorizontally(
            initialOffsetX = { it }, animationSpec = tween(durationMillis = 300)
        )
    }, exitTransition = {
        slideOutHorizontally(
            targetOffsetX = { -it }, animationSpec = tween(durationMillis = 300)
        )
    }, popEnterTransition = {
        slideInHorizontally(
            initialOffsetX = { -it }, animationSpec = tween(durationMillis = 300)
        )
    }, popExitTransition = {
        slideOutHorizontally(
            targetOffsetX = { it }, animationSpec = tween(durationMillis = 300)
        )
    }) {

    }
}