package com.gopaddi.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier: Modifier,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    enabled: Boolean = true,
    textResource: Int? = null,
    textComposable: @Composable () -> Unit = {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = if (textResource != null) stringResource(id = textResource) else "",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.W900,
            color = MaterialTheme.colorScheme.background,
            lineHeight = 22.sp,
            textAlign = TextAlign.Center
        )
    },
    showLoader: MutableState<Boolean> = remember { mutableStateOf(false) },
    onClick: () -> Unit
) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentSize provides Dp.Unspecified
    ) {
        Button(
            modifier = modifier,
            onClick = onClick,
            enabled = enabled,
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                disabledContainerColor = MaterialTheme.colorScheme.inversePrimary
            ),
            contentPadding = PaddingValues(
                vertical = verticalPadding, horizontal = horizontalPadding
            )
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (showLoader.value) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(width = 30.dp),
                        color = MaterialTheme.colorScheme.background,
                        strokeWidth = 5.dp,
                        strokeCap = StrokeCap.Round
                    )
                } else {
                    textComposable()
                }
            }
        }
    }
}