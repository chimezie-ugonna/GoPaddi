package com.gopaddi.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gopaddi.app.R

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    placeHolderResource: Int,
    hasFocus: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    },
    focusRequester: FocusRequester = remember {
        FocusRequester()
    },
    paddingHorizontal: Dp = dimensionResource(id = R.dimen.spacingSm2),
    paddingVertical: Dp = dimensionResource(id = R.dimen.spacingSm2),
    value: MutableState<TextFieldValue> = rememberSaveable { mutableStateOf(TextFieldValue("")) },
    singleLine: Boolean = true,
    placeHolderMaxLines: Int = 1
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clip(
            shape = MaterialTheme.shapes.extraSmall
        )
        .background(
            color = MaterialTheme.colorScheme.background
        )
        .border(
            width = 1.dp,
            color = if (hasFocus.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
            shape = MaterialTheme.shapes.extraSmall
        )
        .clickable(
            interactionSource = remember { MutableInteractionSource() }, indication = null
        ) {
            focusRequester.requestFocus()
        }
        .padding(
            vertical = paddingVertical, horizontal = paddingHorizontal
        ), verticalAlignment = Alignment.CenterVertically) {
        val customTextSelectionColors = TextSelectionColors(
            handleColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.primary
        )
        CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
            BasicTextField(value = value.value,
                onValueChange = {
                    value.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .onFocusChanged { hasFocus.value = it.hasFocus }
                    .focusRequester(focusRequester),
                textStyle = TextStyle(
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.W500,
                    lineHeight = 22.sp
                ),
                singleLine = singleLine,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                decorationBox = { innerTextField ->
                    if (value.value.text.isEmpty()) {
                        Text(
                            text = stringResource(id = placeHolderResource),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.W500,
                            maxLines = placeHolderMaxLines,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 22.sp
                        )
                    }
                    innerTextField()
                })
        }
    }
}