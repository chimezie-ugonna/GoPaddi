package com.gopaddi.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

private val DarkColorScheme = darkColorScheme(
    background = white,
    inverseOnSurface = shimmer,
    inversePrimary = disabledButton,
    onBackground = textBlack,
    onSurface = textGrey,
    onSurfaceVariant = lightGrey,
    onTertiary = iconBlack,
    primary = blue,
    surface = dividerGrey,
    surfaceTint = subTextGrey,
    surfaceVariant = iconGrey,
    tertiaryContainer = snow
)

private val LightColorScheme = lightColorScheme(
    background = white,
    inverseOnSurface = shimmer,
    inversePrimary = disabledButton,
    onBackground = textBlack,
    onSurface = textGrey,
    onSurfaceVariant = lightGrey,
    onTertiary = iconBlack,
    primary = blue,
    surface = dividerGrey,
    surfaceTint = subTextGrey,
    surfaceVariant = iconGrey,
    tertiaryContainer = snow
)

@Composable
fun GoPaddiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    statusBarColor: Color = white,
    isAppearanceLightStatusBars: Boolean = true,
    navigationBarColor: Color = white,
    isAppearanceLightNavigationBars: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = statusBarColor.toArgb()
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
                isAppearanceLightStatusBars
            window.navigationBarColor = navigationBarColor.toArgb()
            WindowInsetsControllerCompat(
                window, window.decorView
            ).isAppearanceLightNavigationBars = !isAppearanceLightNavigationBars
        }
    }

    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, shapes = Shapes, content = content
    )
}