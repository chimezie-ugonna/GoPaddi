@file:OptIn(ExperimentalMaterial3Api::class)

package com.gopaddi.app.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.google.gson.Gson
import com.gopaddi.app.R
import com.gopaddi.app.data.TripData
import com.gopaddi.app.ui.components.CustomBottomSheet
import com.gopaddi.app.ui.components.CustomButton
import com.gopaddi.app.ui.components.CustomSnackBar
import com.gopaddi.app.ui.components.CustomTopBar
import com.gopaddi.app.viewModel.Screen1ViewModel
import kotlinx.coroutines.launch

@Composable
fun Screen1(navController: NavController, viewModel: Screen1ViewModel) {
    Box {
        val snackBarHostState = remember {
            SnackbarHostState()
        }
        val scope = rememberCoroutineScope()
        val snackBarMessageResource = remember {
            mutableIntStateOf(0)
        }
        val snackBarMessage = remember {
            mutableStateOf("")
        }
        val snackBarIsError = remember {
            mutableStateOf(true)
        }

        LaunchedEffect(viewModel.showError) {
            if (viewModel.showError) {
                snackBarIsError.value = true
                snackBarMessageResource.intValue = 0
                snackBarMessage.value = viewModel.errorMessage
                scope.launch { snackBarHostState.showSnackbar("") }
                viewModel.showError(status = false)
            }
        }

        Column {
            CustomTopBar(
                textResource = R.string.plan_a_trip
            )
            LazyColumn {
                item {

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(height = 549.dp),
                            painter = painterResource(id = R.drawable.city),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                                    vertical = dimensionResource(id = R.dimen.spacingMd)
                                )
                                .align(alignment = Alignment.TopCenter),
                            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxs))
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.plan_your_dream_trip_in_minutes),
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.W700,
                                color = MaterialTheme.colorScheme.onBackground,
                                lineHeight = 26.sp
                            )

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.build_personalize_and_optimize_your_itineraries_with_our_trip_planner_perfect_for_getaways_remote_workcations_and_any_spontaneous_escapade),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.W500,
                                color = MaterialTheme.colorScheme.surfaceTint,
                                lineHeight = 22.sp
                            )
                        }

                        val selectedCity = rememberSaveable { mutableStateOf("") }
                        val citySelected =
                            navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
                                "citySelected"
                            )?.observeAsState()
                        citySelected?.value?.let {
                            selectedCity.value = if (it.isNotEmpty()) it else selectedCity.value
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "citySelected", ""
                            )
                        }
                        val selectedStartDate = rememberSaveable { mutableStateOf("") }
                        val startDateSelected =
                            navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
                                "startDateSelected"
                            )?.observeAsState()
                        startDateSelected?.value?.let {
                            selectedStartDate.value =
                                if (it.isNotEmpty()) it else selectedStartDate.value
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "startDateSelected", ""
                            )
                        }
                        val selectedEndDate = rememberSaveable { mutableStateOf("") }
                        val endDateSelected =
                            navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
                                "endDateSelected"
                            )?.observeAsState()
                        endDateSelected?.value?.let {
                            selectedEndDate.value = if (it.isNotEmpty()) it else selectedEndDate.value
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "endDateSelected", ""
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = dimensionResource(id = R.dimen.spacingBg))
                                .align(alignment = Alignment.BottomCenter)
                                .clip(shape = MaterialTheme.shapes.extraSmall)
                                .background(color = MaterialTheme.colorScheme.background)
                                .padding(all = dimensionResource(id = R.dimen.topBarHorizontalPadding)),
                            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxs))
                        ) {

                            Content(
                                modifier = Modifier.fillMaxWidth(),
                                leadingIconResource = R.drawable.mappin,
                                headerTextResource = R.string.where_to,
                                subText = selectedCity.value,
                                subTextResource = R.string.select_city
                            ) {
                                navController.navigate(route = "WhereToScreen")
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(
                                    space = dimensionResource(
                                        id = R.dimen.spacingXxs
                                    )
                                )
                            ) {
                                Content(
                                    modifier = Modifier.weight(weight = 0.5f),
                                    leadingIconResource = R.drawable.calendarblank,
                                    headerTextResource = R.string.start_date,
                                    subText = selectedStartDate.value,
                                    subTextResource = R.string.enter_date
                                ) {
                                    navController.navigate(route = "DateScreen")
                                }

                                Content(
                                    modifier = Modifier.weight(weight = 0.5f),
                                    leadingIconResource = R.drawable.calendarblank,
                                    headerTextResource = R.string.end_date,
                                    subText = selectedEndDate.value,
                                    subTextResource = R.string.enter_date
                                ) {
                                    navController.navigate(route = "DateScreen")
                                }
                            }

                            val createdTripData = rememberSaveable { mutableStateOf<TripData?>(null) }
                            LaunchedEffect(createdTripData.value) {
                                if (createdTripData.value != null) {
                                    val tripData = createdTripData.value
                                    viewModel.tripItems.toMutableList().apply {
                                        if (tripData != null) {
                                            snackBarIsError.value = false
                                            snackBarMessageResource.intValue =
                                                R.string.trip_created_successfully
                                            snackBarMessage.value = ""
                                            scope.launch {
                                                snackBarHostState.showSnackbar("")
                                            }
                                            add(0, tripData)
                                            if (viewModel.isEmpty) {
                                                viewModel.isEmpty(status = false)
                                            }
                                        }
                                    }
                                    createdTripData.value = null
                                }
                            }

                            val showBottomSheet = rememberSaveable { mutableStateOf(false) }

                            val hasPerformedHapticFeedback = rememberSaveable { mutableStateOf(false) }

                            CustomBottomSheet(
                                showBottomSheet = showBottomSheet,
                                hasPerformedHapticFeedback = hasPerformedHapticFeedback,
                                createdTripData = createdTripData
                            )

                            CustomButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height = dimensionResource(id = R.dimen.topBarHeight)),
                                verticalPadding = dimensionResource(id = R.dimen.spacingXxs),
                                horizontalPadding = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                                enabled = selectedCity.value.isNotEmpty() && selectedStartDate.value.isNotEmpty() && selectedEndDate.value.isNotEmpty(),
                                textResource = R.string.create_a_trip
                            ) {
                                showBottomSheet.value = true
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                                top = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                                end = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                            ),
                        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxxs))
                    ) {

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.your_trips),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.W700,
                            color = MaterialTheme.colorScheme.onBackground,
                            lineHeight = 24.sp
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = dimensionResource(id = R.dimen.spacingXxxs)),
                            text = stringResource(R.string.your_trip_itineraries_and_planned_trips_are_placed_here),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.W500,
                            color = MaterialTheme.colorScheme.surfaceTint,
                            lineHeight = 20.sp
                        )

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                                top = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                                end = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                            )
                            .clip(shape = MaterialTheme.shapes.extraSmall)
                            .background(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            .padding(all = dimensionResource(R.dimen.spacingXxs))
                            .clip(shape = MaterialTheme.shapes.extraSmall)
                            .background(color = MaterialTheme.colorScheme.background)
                            .padding(all = dimensionResource(R.dimen.spacingXxs))
                    ) {

                        Text(
                            modifier = Modifier
                                .weight(weight = 1f)
                                .padding(end = dimensionResource(R.dimen.spacingXxs)),
                            text = stringResource(R.string.planned_trips),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.W700,
                            color = MaterialTheme.colorScheme.onBackground,
                            lineHeight = 22.sp
                        )

                        Icon(
                            modifier = Modifier.size(size = dimensionResource(id = R.dimen.topBarVerticalPadding)),
                            painter = painterResource(id = R.drawable.caretdown),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }

                item {
                    val hasLoaded = rememberSaveable { mutableStateOf(false) }
                    LaunchedEffect(key1 = hasLoaded.value) {
                        if (!hasLoaded.value) {
                            viewModel.loadData()
                            hasLoaded.value = true
                        }
                    }
                }

                if (viewModel.isLoading) {
                    items(count = 4) { index ->
                        ShimmerLoaderItem(index = index, isLoading = viewModel.isLoading)
                    }
                } else {
                    if (viewModel.isEmpty) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(
                                        vertical = dimensionResource(id = R.dimen.spacingMd2),
                                        horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                                    ),
                                verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxxs)),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.no_trips_yet),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.W700,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    lineHeight = 24.sp
                                )

                                Text(
                                    text = stringResource(R.string.add_a_trip_to_see_it_here),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.W500,
                                    color = MaterialTheme.colorScheme.surfaceTint,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    } else {
                        itemsIndexed(viewModel.tripItems) { index, item ->
                            TripItem(
                                navController = navController,
                                index = index,
                                item = item,
                                size = viewModel.tripItems.size
                            )
                        }
                    }
                }
            }
        }

        CustomSnackBar(
            messageResource = snackBarMessageResource.intValue,
            isError = snackBarIsError.value,
            message = snackBarMessage.value,
            snackBarHostState = snackBarHostState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun ShimmerLoaderItem(index: Int, isLoading: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                end = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                top = if (index == 0) dimensionResource(id = R.dimen.spacingMd2) else dimensionResource(
                    id = R.dimen.spacingSm
                ),
                bottom = if (index == 3) dimensionResource(
                    id = R.dimen.spacingSm
                ) else 0.dp,
            )
            .clip(shape = MaterialTheme.shapes.extraSmall)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraSmall
            )
            .background(color = MaterialTheme.colorScheme.background)
            .padding(
                horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                vertical = dimensionResource(id = R.dimen.spacingSm)
            ),
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingSm2))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 230.dp)
                .clip(shape = MaterialTheme.shapes.small)
                .background(color = MaterialTheme.colorScheme.surface)
                .placeholder(
                    visible = isLoading, highlight = PlaceholderHighlight.shimmer(
                        highlightColor = MaterialTheme.colorScheme.inverseOnSurface
                    ), color = Color.Transparent
                )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.6f)
                .height(height = dimensionResource(id = R.dimen.spacingMd2))
                .background(color = MaterialTheme.colorScheme.surface)
                .placeholder(
                    visible = isLoading, highlight = PlaceholderHighlight.shimmer(
                        highlightColor = MaterialTheme.colorScheme.inverseOnSurface
                    ), color = Color.Transparent
                )
        )

        Row(modifier = Modifier.fillMaxWidth()) {

            Box(
                modifier = Modifier
                    .weight(weight = 0.6f)
                    .height(height = dimensionResource(id = R.dimen.spacingXmd))
                    .padding(end = dimensionResource(id = R.dimen.spacingBg))
                    .background(color = MaterialTheme.colorScheme.surface)
                    .placeholder(
                        visible = isLoading, highlight = PlaceholderHighlight.shimmer(
                            highlightColor = MaterialTheme.colorScheme.inverseOnSurface
                        ), color = Color.Transparent
                    )
            )

            Box(
                modifier = Modifier
                    .weight(weight = 0.3f)
                    .height(height = dimensionResource(id = R.dimen.spacingXmd))
                    .background(color = MaterialTheme.colorScheme.surface)
                    .placeholder(
                        visible = isLoading, highlight = PlaceholderHighlight.shimmer(
                            highlightColor = MaterialTheme.colorScheme.inverseOnSurface
                        ), color = Color.Transparent
                    )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = dimensionResource(id = R.dimen.buttonHeight))
                .clip(shape = MaterialTheme.shapes.extraSmall)
                .background(color = MaterialTheme.colorScheme.surface)
                .placeholder(
                    visible = isLoading, highlight = PlaceholderHighlight.shimmer(
                        highlightColor = MaterialTheme.colorScheme.inverseOnSurface
                    ), color = Color.Transparent
                )
        )

    }
}

@Composable
fun TripItem(navController: NavController, index: Int, item: TripData, size: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                end = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                top = if (index == 0) dimensionResource(id = R.dimen.spacingMd2) else dimensionResource(
                    id = R.dimen.spacingSm
                ),
                bottom = if (index == size - 1) dimensionResource(
                    id = R.dimen.spacingSm
                ) else 0.dp,
            )
            .clip(shape = MaterialTheme.shapes.extraSmall)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraSmall
            )
            .background(color = MaterialTheme.colorScheme.background)
            .padding(
                horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                vertical = dimensionResource(id = R.dimen.spacingSm)
            ),
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingSm2))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.small
                )
                .clip(shape = MaterialTheme.shapes.small)
        ) {
            AsyncImage(
                model = item.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 230.dp)
                    .clip(shape = MaterialTheme.shapes.small),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                        vertical = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                    )
                    .clip(shape = MaterialTheme.shapes.extraSmall)
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
                        shape = MaterialTheme.shapes.extraSmall
                    )
                    .padding(all = dimensionResource(id = R.dimen.topBarHorizontalPadding))
            ) {
                Text(
                    text = item.location.split(", ")[0],
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.background,
                    lineHeight = 22.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = item.tripName,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.W700,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 24.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(end = dimensionResource(id = R.dimen.spacingBg)),
                text = item.startDate,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Text(
                text = item.duration,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.surfaceTint,
                lineHeight = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End
            )
        }

        CustomButton(modifier = Modifier
            .fillMaxWidth()
            .height(height = dimensionResource(id = R.dimen.buttonHeight)),
            verticalPadding = dimensionResource(id = R.dimen.spacingXxs),
            horizontalPadding = dimensionResource(id = R.dimen.topBarHorizontalPadding),
            textComposable = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.view),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.background,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center
                )
            }) {
            navController.navigate(
                route = "TripInfoScreen/${
                    Uri.encode(
                        Gson().toJson(
                            item
                        )
                    )
                }"
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier,
    leadingIconResource: Int,
    headerTextResource: Int,
    subText: String,
    subTextResource: Int,
    onClick: () -> Unit
) {
    Row(modifier = modifier
        .clip(shape = MaterialTheme.shapes.extraSmall)
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.extraSmall
        )
        .clickable { onClick() }
        .background(color = MaterialTheme.colorScheme.tertiaryContainer)
        .padding(
            horizontal = dimensionResource(id = R.dimen.spacingXs),
            vertical = dimensionResource(R.dimen.iconSize)
        ),
        horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxs)),
        verticalAlignment = Alignment.CenterVertically) {

        Icon(
            modifier = Modifier.size(size = dimensionResource(id = R.dimen.iconSize)),
            painter = painterResource(id = leadingIconResource),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.surfaceVariant
        )

        Column(
            modifier = Modifier.weight(weight = 1f),
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxxs))
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = headerTextResource),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (subText == "") stringResource(id = subTextResource) else subText,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.W900,
                color = MaterialTheme.colorScheme.surfaceTint,
                lineHeight = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}