package com.gopaddi.app.data

import java.util.UUID

data class TripData(
    val id: String = UUID.randomUUID().toString(),
    var tripName: String = "",
    var tripDescription: String = "",
    var location: String = "",
    var travelStyle: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var duration: String = "",
    var imageUrl: String = ""
)
