package com.gopaddi.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.gopaddi.app.R
import com.gopaddi.app.data.TripData
import com.gopaddi.app.ui.components.CustomButton
import com.gopaddi.app.ui.components.CustomTopBar

@Composable
fun TripInfoScreen(navController: NavController, tripData: TripData) {
    Column {
        CustomTopBar(
            leadingIconResource = R.drawable.arrowleft, leadingIconOnClick = {
                navController.popBackStack()
            }, textResource = R.string.trip_information
        )

        LazyColumn {
            item {
                AsyncImage(
                    model = tripData.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 235.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.surface
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.iconSize),
                            start = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                            end = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                        ),
                    verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxs))
                ) {

                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(shape = MaterialTheme.shapes.extraSmall)
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = MaterialTheme.shapes.extraSmall
                            )
                            .padding(all = dimensionResource(id = R.dimen.spacingXxs)),
                        horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxxs)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.size(size = dimensionResource(id = R.dimen.spacingSm)),
                            painter = painterResource(R.drawable.calendarblank),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )

                        Text(
                            modifier = Modifier.wrapContentSize(),
                            text = tripData.startDate,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.W500,
                            color = MaterialTheme.colorScheme.onBackground,
                            lineHeight = 20.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Icon(
                            modifier = Modifier.size(size = dimensionResource(id = R.dimen.spacingSm)),
                            painter = painterResource(R.drawable.arrowright),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )

                        Text(
                            modifier = Modifier.wrapContentSize(),
                            text = tripData.endDate,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.W500,
                            color = MaterialTheme.colorScheme.onBackground,
                            lineHeight = 20.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = tripData.tripName,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.W700,
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = 24.sp
                    )

                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = "${tripData.location} | ${tripData.travelStyle}",
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
                            all = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                        ),
                    horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingSm)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier.weight(weight = 1f),
                        painterResource = R.drawable.handshake,
                        stringResource = R.string.trip_collaboration
                    )
                    Button(
                        modifier = Modifier.weight(weight = 1f),
                        painterResource = R.drawable.sharefat,
                        stringResource = R.string.share_trip
                    )
                    Icon(
                        modifier = Modifier.size(size = dimensionResource(id = R.dimen.spacingMd2)),
                        painter = painterResource(R.drawable.dotsthree),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = dimensionResource(id = R.dimen.spacingSm),
                            horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                        ),
                    verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxs))
                ) {
                    Category(
                        stringResource = R.string.activities,
                        backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Category(
                        stringResource = R.string.hotels,
                        textColor = MaterialTheme.colorScheme.onBackground,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer
                    )
                    Category(
                        stringResource = R.string.flights,
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        buttonTextColor = MaterialTheme.colorScheme.primary,
                        buttonBackgroundColor = MaterialTheme.colorScheme.background
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = dimensionResource(id = R.dimen.spacingXs),
                            horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                        ),
                    verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.topBarHorizontalPadding))
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXxxs))
                    ) {

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.trip_itineraries),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.W700,
                            color = MaterialTheme.colorScheme.onBackground,
                            lineHeight = 24.sp
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = dimensionResource(id = R.dimen.spacingXxxs)),
                            text = stringResource(R.string.your_trip_itineraries_are_placed_here),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.W500,
                            color = MaterialTheme.colorScheme.surfaceTint,
                            lineHeight = 20.sp
                        )

                    }

                    Category2(
                        painterResource = R.drawable.airplaneinflight,
                        stringResource = R.string.flights,
                        iconTint = MaterialTheme.colorScheme.onBackground,
                        textColor = MaterialTheme.colorScheme.onBackground,
                        backgroundColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        painterResource2 = R.drawable.plane_wing,
                        stringResource2 = R.string.flight
                    )
                    Category2(
                        painterResource = R.drawable.buildings,
                        stringResource = R.string.hotels,
                        backgroundColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        painterResource2 = R.drawable.hotel_building,
                        stringResource2 = R.string.hotel
                    )
                    Category2(
                        painterResource = R.drawable.roadhorizon,
                        stringResource = R.string.activities,
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        painterResource2 = R.drawable.air_balloon,
                        stringResource2 = R.string.activity
                    )
                }
            }
        }
    }
}

@Composable
fun Button(modifier: Modifier = Modifier, painterResource: Int, stringResource: Int) {

    Box(modifier = modifier
        .clip(shape = MaterialTheme.shapes.extraSmall)
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = MaterialTheme.shapes.extraSmall
        )
        .clickable {

        }
        .padding(
            all = dimensionResource(id = R.dimen.topBarHorizontalPadding)
        ), contentAlignment = Alignment.Center) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(size = dimensionResource(id = R.dimen.iconSize)),
                painter = painterResource(painterResource),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = stringResource(stringResource),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.primary,
                lineHeight = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun Category(
    stringResource: Int,
    textColor: Color = MaterialTheme.colorScheme.background,
    backgroundColor: Color,
    buttonTextColor: Color = MaterialTheme.colorScheme.background,
    buttonBackgroundColor: Color = MaterialTheme.colorScheme.primary
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.extraSmall)
            .background(color = backgroundColor, shape = MaterialTheme.shapes.extraSmall)
            .padding(
                vertical = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                horizontal = dimensionResource(id = R.dimen.spacingSm2)
            )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = stringResource),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.W700,
            color = textColor,
            lineHeight = 24.sp
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.spacingXxs)),
            text = stringResource(R.string.build_personalize_and_optimize_your_itineraries_with_our_trip_planner),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.W400,
            color = textColor,
            lineHeight = 22.sp
        )

        CustomButton(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 37.dp)
            .height(height = dimensionResource(id = R.dimen.buttonHeight)),
            containerColor = buttonBackgroundColor,
            verticalPadding = dimensionResource(id = R.dimen.spacingSm),
            horizontalPadding = dimensionResource(id = R.dimen.spacingMd2),
            textComposable = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.add, stringResource(id = stringResource)),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.W500,
                    color = buttonTextColor,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center
                )
            }) {

        }
    }
}

@Composable
fun Category2(
    painterResource: Int,
    iconTint: Color = MaterialTheme.colorScheme.background,
    stringResource: Int,
    stringResource2: Int,
    textColor: Color = MaterialTheme.colorScheme.background,
    backgroundColor: Color,
    painterResource2: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.extraSmall)
            .background(color = backgroundColor, shape = MaterialTheme.shapes.extraSmall)
            .padding(all = dimensionResource(id = R.dimen.topBarHorizontalPadding))
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(size = dimensionResource(id = R.dimen.spacingMd2)),
                painter = painterResource(id = painterResource),
                contentDescription = null,
                tint = iconTint
            )

            Text(
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(start = dimensionResource(id = R.dimen.spacingXxs)),
                text = stringResource(id = stringResource),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.W700,
                color = textColor,
                lineHeight = 24.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.topBarHorizontalPadding))
                .clip(shape = MaterialTheme.shapes.extraSmall)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .padding(
                    horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                    vertical = 53.dp
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)),
                verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.spacingXs)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(size = 150.dp),
                    painter = painterResource(id = painterResource2),
                    contentDescription = null
                )

                Text(
                    text = stringResource(R.string.no_request_yet),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 24.sp
                )

                CustomButton(modifier = Modifier
                    .fillMaxWidth()
                    .height(height = dimensionResource(id = R.dimen.buttonHeight)),
                    verticalPadding = dimensionResource(id = R.dimen.spacingXxs),
                    horizontalPadding = dimensionResource(id = R.dimen.topBarHorizontalPadding),
                    textComposable = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(
                                R.string.add, stringResource(id = stringResource2)
                            ),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.W700,
                            color = MaterialTheme.colorScheme.background,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.Center
                        )
                    }) {

                }
            }

        }
    }
}