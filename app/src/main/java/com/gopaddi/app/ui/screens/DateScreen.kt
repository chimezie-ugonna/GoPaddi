package com.gopaddi.app.ui.screens

import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gopaddi.app.R
import com.gopaddi.app.ui.components.CustomButton
import com.gopaddi.app.ui.components.CustomTopBar
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun DateScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopBar(
            leadingIconResource = R.drawable.x,
            leadingIconOnClick = {
                navController.popBackStack()
            },
            leadingIconContentDescription = R.string.close_screen_icon,
            textResource = R.string.date
        )

        val startDate = rememberSaveable { mutableStateOf("") }
        val endDate = rememberSaveable { mutableStateOf("") }
        val duration = rememberSaveable { mutableStateOf("") }
        val view = LocalView.current

        FullScreenCalendar(view = view, modifier = Modifier.weight(weight = 1f)) { s, e, d ->
            startDate.value = s
            endDate.value = e
            duration.value = d
        }

        val hapticFeedbackPerformed = rememberSaveable { mutableStateOf(false) }
        LaunchedEffect(key1 = hapticFeedbackPerformed.value) {
            if (!hapticFeedbackPerformed.value) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                hapticFeedbackPerformed.value = true
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
                    .padding(all = dimensionResource(id = R.dimen.topBarHorizontalPadding)),
                verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.topBarHorizontalPadding))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxs))
                ) {
                    DateField(
                        modifier = Modifier.weight(weight = 1f),
                        titleTextResource = R.string.start_date,
                        date = startDate.value
                    )
                    DateField(
                        modifier = Modifier.weight(weight = 1f),
                        titleTextResource = R.string.end_date,
                        date = endDate.value
                    )
                }
                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = dimensionResource(id = R.dimen.buttonHeight)),
                    verticalPadding = dimensionResource(id = R.dimen.spacingXxs),
                    horizontalPadding = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                    enabled = startDate.value.isNotEmpty() && endDate.value.isNotEmpty(),
                    textResource = R.string.choose_date
                ) {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "startDateSelected", startDate.value
                    )
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "endDateSelected", endDate.value
                    )
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "durationSelected", duration.value
                    )
                    navController.popBackStack()
                }
            }

        }
    }
}

@Composable
fun DateField(modifier: Modifier, titleTextResource: Int, date: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxxs))
    ) {
        Text(
            text = stringResource(titleTextResource),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.W500,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 20.sp,
            textAlign = TextAlign.Start
        )

        Row(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.extraSmall)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .padding(all = dimensionResource(id = R.dimen.spacingSm2))
        ) {

            Text(
                text = if (date.isEmpty()) stringResource(R.string.not_set) else date,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = dimensionResource(id = R.dimen.spacingSm2)),
                lineHeight = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                modifier = Modifier.size(size = dimensionResource(id = R.dimen.topBarHorizontalPadding)),
                painter = painterResource(id = R.drawable.calendarblank),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun FullScreenCalendar(
    view: View, modifier: Modifier = Modifier, onDateSelected: (String, String, String) -> Unit
) {
    val currentDate = remember { LocalDate.now() }
    val selectedStartDate = rememberSaveable { mutableStateOf<LocalDate?>(null) }
    val selectedEndDate = rememberSaveable { mutableStateOf<LocalDate?>(null) }
    val dateFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(12) { monthOffset ->
            val displayedMonth = currentDate.plusMonths(monthOffset.toLong())

            Text(text = "${
                displayedMonth.month.name.lowercase().replaceFirstChar { it.uppercase() }
            } ${displayedMonth.year}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.W700,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = if (monthOffset == 0) dimensionResource(id = R.dimen.topBarHorizontalPadding) else dimensionResource(
                            id = R.dimen.spacingSm
                        ), bottom = dimensionResource(id = R.dimen.spacingXxs)
                    ))

            CalendarMonth(monthOffset = monthOffset,
                view = view,
                month = displayedMonth.month,
                year = displayedMonth.year,
                selectedStartDate = selectedStartDate.value,
                selectedEndDate = selectedEndDate.value,
                onDateClick = { clickedDate ->
                    if (selectedStartDate.value == null || selectedStartDate.value != null && selectedEndDate.value != null || selectedStartDate.value != null && clickedDate.isBefore(
                            selectedStartDate.value
                        )
                    ) {
                        selectedStartDate.value = clickedDate
                        selectedEndDate.value = null
                    } else if (selectedEndDate.value == null) {
                        selectedEndDate.value = clickedDate
                    }

                    onDateSelected(
                        selectedStartDate.value?.format(dateFormatter).orEmpty(),
                        selectedEndDate.value?.format(dateFormatter).orEmpty(),
                        if (selectedStartDate.value != null && selectedEndDate.value != null) {
                            val duration = ChronoUnit.DAYS.between(
                                selectedStartDate.value, selectedEndDate.value
                            ) + 1
                            "$duration ${if (duration == 1L) "day" else "days"}"
                        } else ""
                    )
                })
        }
    }
}

@Composable
fun CalendarMonth(
    monthOffset: Int,
    view: View,
    month: Month,
    year: Int,
    selectedStartDate: LocalDate?,
    selectedEndDate: LocalDate?,
    onDateClick: (LocalDate) -> Unit
) {
    val firstDayOfMonth = LocalDate.of(year, month, 1)
    val daysInMonth = firstDayOfMonth.lengthOfMonth()
    val startDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7

    val daysGrid = (1..daysInMonth).map { firstDayOfMonth.plusDays(it.toLong() - 1) }

    val fillerDaysBefore =
        (1..startDayOfWeek).map { firstDayOfMonth.minusDays(it.toLong()) }.reversed()

    val fillerDaysAfter = (1..(7 - (daysGrid.size + fillerDaysBefore.size) % 7) % 7).map {
        firstDayOfMonth.plusDays(daysInMonth.toLong() - 1 + it)
    }

    val allDays = fillerDaysBefore + daysGrid + fillerDaysAfter

    Column(
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxs))
        ) {
            listOf("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa").forEach {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(height = dimensionResource(id = R.dimen.spacingXxs)))

        allDays.chunked(7).forEachIndexed { weekIndex, week ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxs)),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                week.forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                top = dimensionResource(id = R.dimen.spacingXxxs),
                                bottom = if (monthOffset == 11 && weekIndex == allDays.chunked(7).size - 1) dimensionResource(
                                    id = R.dimen.topBarHorizontalPadding
                                ) else dimensionResource(
                                    id = R.dimen.spacingXxxs
                                )
                            )
                            .heightIn(min = 40.dp)
                            .clip(MaterialTheme.shapes.extraSmall)
                            .background(
                                color = when (day) {
                                    null -> MaterialTheme.colorScheme.tertiaryContainer
                                    selectedStartDate, selectedEndDate -> MaterialTheme.colorScheme.primary
                                    else -> MaterialTheme.colorScheme.secondaryContainer
                                }
                            )
                            .clickable(enabled = day != null && !day.isBefore(LocalDate.now())) {
                                day?.let { onDateClick(it) }
                                view.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                            }, contentAlignment = Alignment.Center
                    ) {
                        day?.let {
                            Text(
                                text = day.dayOfMonth.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = when {
                                    it == selectedStartDate || it == selectedEndDate -> MaterialTheme.colorScheme.background
                                    it.isBefore(LocalDate.now()) || fillerDaysBefore.contains(
                                        element = it
                                    ) || fillerDaysAfter.contains(
                                        element = it
                                    ) -> MaterialTheme.colorScheme.surfaceDim

                                    else -> MaterialTheme.colorScheme.onBackground
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}