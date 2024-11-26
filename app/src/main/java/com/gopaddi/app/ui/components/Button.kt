package com.gopaddi.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
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
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.inversePrimary
            )
        ) {
            textComposable()
        }
    }
}