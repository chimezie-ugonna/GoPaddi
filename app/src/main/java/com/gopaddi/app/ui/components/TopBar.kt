package com.gopaddi.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gopaddi.app.R

@Composable
fun CustomTopBar(
    leadingIconResource: Int? = null, leadingIconOnClick: () -> Unit = {}, textResource: Int
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = dimensionResource(R.dimen.topBarHeight))
                .background(color = MaterialTheme.colorScheme.background)
                .padding(
                    vertical = dimensionResource(id = R.dimen.topBarVerticalPadding),
                    horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                ),
            horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingSm)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIconResource != null) {
                Icon(
                    modifier = Modifier
                        .size(size = dimensionResource(id = R.dimen.iconSize))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { leadingIconOnClick() },
                    painter = painterResource(id = leadingIconResource),
                    contentDescription = stringResource(
                        R.string.an_arrow_back_icon
                    ),
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }

            Text(
                modifier = Modifier.weight(weight = 1f),
                text = stringResource(id = textResource),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.W700,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 26.sp
            )
        }

        HorizontalDivider(
            thickness = 1.dp, color = MaterialTheme.colorScheme.surface
        )
    }
}