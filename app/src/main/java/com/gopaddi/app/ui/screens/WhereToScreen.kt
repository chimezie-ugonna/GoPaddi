package com.gopaddi.app.ui.screens

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gopaddi.app.R
import com.gopaddi.app.data.SearchData
import com.gopaddi.app.ui.components.CustomTextField
import com.gopaddi.app.ui.components.CustomTopBar
import com.gopaddi.app.viewModel.WhereToViewModel

@Composable
fun WhereToScreen(navController: NavController, viewModel: WhereToViewModel) {
    Column {
        CustomTopBar(
            leadingIconResource = R.drawable.x,
            leadingIconOnClick = {
                navController.popBackStack()
            },
            leadingIconContentDescription = R.string.close_screen_icon,
            textResource = R.string.where
        )

        val searchFocusRequester = remember {
            FocusRequester()
        }

        val cities = listOf(
            SearchData(
                "New York, USA", "JFK - John F. Kennedy International Airport", "US", R.drawable.us
            ),
            SearchData(
                "Los Angeles, USA", "LAX - Los Angeles International Airport", "US", R.drawable.us
            ),
            SearchData("Tokyo, Japan", "HND - Haneda Airport", "JP", R.drawable.jp),
            SearchData("London, UK", "LHR - Heathrow Airport", "GB", R.drawable.gb),
            SearchData("Paris, France", "CDG - Charles de Gaulle Airport", "FR", R.drawable.fr),
            SearchData("Sydney, Australia", "SYD - Sydney Airport", "AU", R.drawable.au),
            SearchData("Berlin, Germany", "TXL - Tegel Airport", "DE", R.drawable.de),
            SearchData("Dubai, UAE", "DXB - Dubai International Airport", "AE", R.drawable.ae),
            SearchData(
                "Lagos, Nigeria",
                "LOS - Murtala Muhammed International Airport",
                "NG",
                R.drawable.ng
            ),
            SearchData(
                "Rio de Janeiro, Brazil", "GIG - Galeão International Airport", "BR", R.drawable.br
            ),
            SearchData(
                "Rome, Italy", "FCO - Leonardo da Vinci International Airport", "IT", R.drawable.it
            )
        )

        val filteredSuggestions = cities.filter {
            it.city.contains(
                viewModel.searchValue.value.text, ignoreCase = true
            ) || it.airport.contains(
                viewModel.searchValue.value.text, ignoreCase = true
            ) || it.isoCode.contains(viewModel.searchValue.value.text, ignoreCase = true)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(id = R.dimen.iconSize),
                    horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                ),
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.topBarHorizontalPadding))
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = dimensionResource(R.dimen.spacingXxs)),
                text = stringResource(R.string.please_select_a_city),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp,
                textAlign = TextAlign.Start
            )

            CustomTextField(
                placeHolderResource = R.string.search_for_a_city,
                value = viewModel.searchValue,
                focusRequester = searchFocusRequester,
                paddingVertical = dimensionResource(id = R.dimen.spacingMd3),
                paddingHorizontal = dimensionResource(id = R.dimen.iconSize)
            )
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(filteredSuggestions) { _, suggestion ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                    )
                    .clickable {
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "citySelected", suggestion.city
                        )
                        navController.popBackStack()
                    }
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(
                        vertical = dimensionResource(id = R.dimen.spacingMd3),
                        horizontal = dimensionResource(id = R.dimen.iconSize)
                    ), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(size = dimensionResource(id = R.dimen.topBarVerticalPadding)),
                        painter = painterResource(id = R.drawable.mappin2),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiary
                    )

                    Column(
                        modifier = Modifier
                            .weight(weight = 1f)
                            .padding(horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding))
                    ) {
                        Text(
                            modifier = Modifier.padding(end = dimensionResource(R.dimen.spacingXxs)),
                            text = highlightText(suggestion.city, viewModel.searchValue.value.text),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onBackground,
                            lineHeight = 24.sp,
                            textAlign = TextAlign.Start
                        )

                        Text(
                            modifier = Modifier.padding(end = dimensionResource(R.dimen.spacingXxs)),
                            text = highlightText(
                                suggestion.airport, viewModel.searchValue.value.text
                            ),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surfaceTint,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.Start
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(space = 2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.size(size = dimensionResource(id = R.dimen.topBarVerticalPadding)),
                            painter = painterResource(id = suggestion.countryIconResource),
                            contentDescription = null
                        )

                        Text(
                            text = highlightText(
                                suggestion.isoCode, viewModel.searchValue.value.text
                            ),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surfaceTint,
                            lineHeight = 22.sp,
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

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

fun highlightText(text: String, query: String): AnnotatedString {
    if (query.isBlank()) {
        return AnnotatedString(text)
    }

    val annotatedString = buildAnnotatedString {
        var currentIndex = 0
        var startIndex: Int
        while (currentIndex < text.length) {
            startIndex = text.indexOf(query, currentIndex, ignoreCase = true)
            if (startIndex == -1) {
                append(text.substring(currentIndex))
                break
            }

            append(text.substring(currentIndex, startIndex))
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
            append(text.substring(startIndex, startIndex + query.length))
            pop()
            currentIndex = startIndex + query.length
        }
    }
    return annotatedString
}