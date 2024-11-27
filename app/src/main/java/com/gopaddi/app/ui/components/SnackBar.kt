package com.gopaddi.app.ui.components

import android.os.Build
import android.view.HapticFeedbackConstants
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gopaddi.app.R

@Composable
fun CustomSnackBar(
    modifier: Modifier = Modifier,
    isError: Boolean = true,
    messageResource: Int,
    message: String = "",
    snackBarHostState: SnackbarHostState
) {
    SnackbarHost(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)
            ), hostState = snackBarHostState
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(MaterialTheme.shapes.small)
                .background(if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surfaceBright)
                .padding(
                    all = dimensionResource(
                        id = R.dimen.topBarHorizontalPadding
                    )
                ), verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
                painter = painterResource(id = if (isError) R.drawable.error_icon else R.drawable.checkmark_icon),
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(id = R.dimen.topBarHorizontalPadding)),
                text = if (messageResource != 0) stringResource(id = messageResource) else message,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.background,
                lineHeight = 22.sp,
                textAlign = TextAlign.Start
            )

            val view = LocalView.current
            if (Build.VERSION.SDK_INT >= 30) {
                view.performHapticFeedback(if (isError) HapticFeedbackConstants.REJECT else HapticFeedbackConstants.CONFIRM)
            }
        }
    }
}