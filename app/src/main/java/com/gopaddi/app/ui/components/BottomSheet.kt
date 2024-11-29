@file:OptIn(ExperimentalMaterial3Api::class)

package com.gopaddi.app.ui.components

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat
import com.gopaddi.app.R
import com.gopaddi.app.viewModel.Screen1ViewModel
import kotlinx.coroutines.launch

@Composable
fun CustomBottomSheet(
    showBottomSheet: MutableState<Boolean>,
    hasPerformedHapticFeedback: MutableState<Boolean>? = null,
    viewModel: Screen1ViewModel,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
) {
    if (showBottomSheet.value) {
        val context = LocalContext.current
        val view = LocalView.current
        val scope = rememberCoroutineScope()
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
                if (hasPerformedHapticFeedback != null) {
                    hasPerformedHapticFeedback.value = false
                }
            },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background,
            dragHandle = null,
            shape = RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.spacingXxxs),
                topEnd = dimensionResource(id = R.dimen.spacingXxxs)
            ),
            modifier = Modifier.statusBarsPadding()
        ) {
            val view2 = LocalView.current
            (view2.parent as? DialogWindowProvider)?.window?.let { window ->
                SideEffect {
                    WindowCompat.getInsetsController(window, view2).isAppearanceLightStatusBars =
                        false
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                        top = 29.dp,
                        end = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                    ), verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .clip(shape = MaterialTheme.shapes.extraSmall)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.extraSmall
                        )
                        .padding(all = dimensionResource(id = R.dimen.spacingSm)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(size = dimensionResource(id = R.dimen.iconSize)),
                        painter = painterResource(id = R.drawable.treepalm),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(
                    modifier = Modifier
                        .weight(weight = 1f)
                        .padding(horizontal = dimensionResource(R.dimen.topBarHorizontalPadding))
                )

                Icon(
                    modifier = Modifier
                        .size(size = dimensionResource(id = R.dimen.iconSize))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            scope
                                .launch { sheetState.hide() }
                                .invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet.value = false
                                        if (hasPerformedHapticFeedback != null) {
                                            hasPerformedHapticFeedback.value = false
                                        }
                                    }
                                }
                        },
                    painter = painterResource(id = R.drawable.x),
                    contentDescription = stringResource(R.string.close_bottom_sheet_icon),
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }

            val selectedTravelStyle = rememberSaveable { mutableStateOf("") }

            LazyColumn(
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(
                        start = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                        top = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                        end = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                    )
            ) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.create_a_trip),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.W700,
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Start
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(id = R.dimen.spacingXxxs)),
                        text = stringResource(R.string.let_s_go_build_your_next_adventure),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Start
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(id = R.dimen.iconSize)),
                        text = stringResource(R.string.trip_name),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Start
                    )

                    CustomTextField(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacingXxxs)),
                        placeHolderResource = R.string.enter_the_trip_name,
                        value = viewModel.tripName,
                        focusRequester = remember {
                            FocusRequester()
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next
                        ),
                        paddingVertical = dimensionResource(id = R.dimen.spacingSm2),
                        paddingHorizontal = dimensionResource(id = R.dimen.spacingSm2)
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(id = R.dimen.iconSize)),
                        text = stringResource(R.string.travel_style),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Start
                    )

                    val focusManager = LocalFocusManager.current
                    val focusRequester = remember {
                        FocusRequester()
                    }
                    val expanded = rememberSaveable {
                        mutableStateOf(false)
                    }
                    val hasFocus = rememberSaveable {
                        mutableStateOf(false)
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(id = R.dimen.spacingXxxs))
                            .clip(shape = MaterialTheme.shapes.extraSmall)
                            .border(
                                width = 1.dp,
                                color = if (hasFocus.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                shape = MaterialTheme.shapes.extraSmall
                            )
                            .onFocusChanged { hasFocus.value = it.hasFocus }
                            .focusRequester(focusRequester = focusRequester)
                            .focusable()
                            .clickable {
                                focusRequester.requestFocus()
                                expanded.value = true
                            }
                            .background(color = MaterialTheme.colorScheme.background)
                            .padding(all = dimensionResource(id = R.dimen.spacingSm2)),
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                modifier = Modifier
                                    .weight(weight = 1f)
                                    .padding(end = dimensionResource(id = R.dimen.spacingSm2)),
                                text = if (selectedTravelStyle.value.isEmpty()) stringResource(R.string.select_your_travel_style) else selectedTravelStyle.value,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.W500,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = if (selectedTravelStyle.value.isEmpty()) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onBackground,
                                lineHeight = 22.sp,
                                textAlign = TextAlign.Start
                            )

                            Icon(
                                modifier = Modifier.size(size = dimensionResource(id = R.dimen.topBarHorizontalPadding)),
                                painter = painterResource(id = R.drawable.caretdown2),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.outline
                            )
                        }
                        val selectedItemIndex = rememberSaveable { mutableIntStateOf(-1) }
                        val items = listOf("Solo", "Couple", "Family", "Group").sortedBy { it }

                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = {
                                expanded.value = false
                                focusManager.clearFocus()
                            },
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                                .padding(horizontal = 6.dp)
                        ) {
                            items.forEachIndexed { index, item ->
                                val isSelected = index == selectedItemIndex.intValue
                                DropdownMenuItem(onClick = {
                                    selectedItemIndex.intValue = index
                                    selectedTravelStyle.value = item
                                    expanded.value = false
                                }, modifier = Modifier.background(
                                    if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                                ), text = {
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Text(
                                            modifier = Modifier
                                                .weight(weight = 1f)
                                                .padding(end = dimensionResource(id = R.dimen.topBarHorizontalPadding)),
                                            text = item,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.W500,
                                            color = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
                                            lineHeight = 22.sp,
                                            textAlign = TextAlign.Start
                                        )

                                        Icon(
                                            modifier = Modifier.size(
                                                size = dimensionResource(
                                                    id = R.dimen.topBarHorizontalPadding
                                                )
                                            ),
                                            painter = painterResource(R.drawable.check),
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.background
                                        )
                                    }
                                })
                            }
                        }
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(id = R.dimen.iconSize)),
                        text = stringResource(R.string.trip_description),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Start
                    )

                    CustomTextField(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.spacingXxxs),
                            bottom = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                        ),
                        placeHolderResource = R.string.tell_us_more_about_the_trip,
                        value = viewModel.tripDescription,
                        focusRequester = remember {
                            FocusRequester()
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        height = 200.dp,
                        paddingVertical = dimensionResource(id = R.dimen.spacingSm2),
                        paddingHorizontal = dimensionResource(id = R.dimen.spacingSm2),
                        singleLine = false,
                        placeHolderMaxLines = Int.MAX_VALUE
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.surface
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(all = dimensionResource(id = R.dimen.topBarHorizontalPadding))
                ) {
                    val showLoader = rememberSaveable { mutableStateOf(false) }
                    CustomButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = dimensionResource(id = R.dimen.buttonHeight)),
                        verticalPadding = dimensionResource(id = R.dimen.spacingXxs),
                        horizontalPadding = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                        enabled = viewModel.tripName.value.text.isNotEmpty() && selectedTravelStyle.value.isNotEmpty() && viewModel.tripDescription.value.text.isNotEmpty(),
                        textResource = R.string.create,
                        showLoader = showLoader
                    ) {
                        if (!showLoader.value) {
                            showLoader.value = true
                            viewModel.updateTripData(
                                tripName = viewModel.tripName.value.text.trim(),
                                tripDescription = viewModel.tripDescription.value.text.trim(),
                                travelStyle = selectedTravelStyle.value
                            )
                            viewModel.createTrip(context = context, onComplete = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet.value = false
                                        if (hasPerformedHapticFeedback != null) {
                                            hasPerformedHapticFeedback.value = false
                                        }
                                    }
                                }
                            })
                        }
                    }
                }

            }
        }
        if (hasPerformedHapticFeedback != null && !hasPerformedHapticFeedback.value) {
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            hasPerformedHapticFeedback.value = true
        }
    }
}