package com.gopaddi.app.ui.screens

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavController
import com.gopaddi.app.R
import com.gopaddi.app.ui.components.CustomTopBar

@Composable
fun WhereToScreen(navController: NavController) {
    Column {
        CustomTopBar(
            leadingIconResource = R.drawable.arrowleft, leadingIconOnClick = {
                navController.popBackStack()
            }, textResource = R.string.where
        )

        val view = LocalView.current
        val hapticFeedbackPerformed = rememberSaveable { mutableStateOf(false) }
        LaunchedEffect(key1 = hapticFeedbackPerformed.value) {
            if (!hapticFeedbackPerformed.value) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                hapticFeedbackPerformed.value = true
            }
        }
    }
}