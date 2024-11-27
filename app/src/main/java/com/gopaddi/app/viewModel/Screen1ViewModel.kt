package com.gopaddi.app.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gopaddi.app.data.TripData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Screen1ViewModel : ViewModel() {

    var tripItems by mutableStateOf(emptyList<TripData>())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var isEmpty by mutableStateOf(false)
        private set

    var showError by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun loadData() {
        viewModelScope.launch {
            isLoading = true
            tripItems = listOf(
                TripData(
                    tripName = "Summer Vacation in Bali",
                    tripDescription = "A relaxing trip to Bali, exploring beaches, temples, and local culture.",
                    location = "Bali, Indonesia",
                    travelStyle = "Leisure",
                    startDate = "1st June 2024",
                    endDate = "10th June 2024",
                    duration = "10 days",
                    imageUrl = "https://media.istockphoto.com/id/1876784184/photo/ulun-danu-beratan-temple-at-sunrise-bali-indonesia.webp?a=1&b=1&s=612x612&w=0&k=20&c=9WJN1mcoyt4CDatbCJ03CE36PNWwsxCiEIosfDQPlqg="
                ), TripData(
                    tripName = "Adventurous Hiking in the Alps",
                    tripDescription = "A challenging yet rewarding hiking trip through the stunning Swiss Alps.",
                    location = "Zermatt, Switzerland",
                    travelStyle = "Adventure",
                    startDate = "15th July 2024",
                    endDate = "25th July 2024",
                    duration = "10 days",
                    imageUrl = "https://media.istockphoto.com/id/992811902/photo/zermatt-town-with-matterhorn-peak-in-mattertal-switzerland-at-dawn.webp?a=1&b=1&s=612x612&w=0&k=20&c=3GeTJ29Szs1w8uKyK-3Agg-28Z9wDIQJKq6D2Ls8Mp0="
                ), TripData(
                    tripName = "Cultural Journey in Kyoto",
                    tripDescription = "Explore the ancient temples and serene gardens of Kyoto, Japan.",
                    location = "Kyoto, Japan",
                    travelStyle = "Cultural",
                    startDate = "10th May 2024",
                    endDate = "20th May 2024",
                    duration = "10 days",
                    imageUrl = "https://media.istockphoto.com/id/902966276/photo/kyoto-japan-in-spring.webp?a=1&b=1&s=612x612&w=0&k=20&c=JB7RcfFl3kdrjRdstsof6oKp4TesN-ObSQ4GhzxSTtI="
                ), TripData(
                    tripName = "Beach Vacation in Maldives",
                    tripDescription = "A tropical getaway to the pristine beaches and overwater bungalows of the Maldives.",
                    location = "Malé, Maldives",
                    travelStyle = "Relaxation",
                    startDate = "1st August 2024",
                    endDate = "7th August 2024",
                    duration = "7 days",
                    imageUrl = "https://plus.unsplash.com/premium_photo-1666286163385-abe05f0326c4?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8TWFsJUMzJUE5JTJDJTIwTWFsZGl2ZXN8ZW58MHx8MHx8fDA%3D"
                ), TripData(
                    tripName = "Exploring New York City",
                    tripDescription = "A city adventure to the iconic landmarks and cultural hubs of New York City.",
                    location = "New York City, USA",
                    travelStyle = "City Tour",
                    startDate = "10th September 2024",
                    endDate = "17th September 2024",
                    duration = "7 days",
                    imageUrl = "https://media.istockphoto.com/id/2156176090/photo/times-square-in-new-york-city.webp?a=1&b=1&s=612x612&w=0&k=20&c=SHApRJVb_bW5-Iv-iQ-ArKrHlkl365xw-kEYggFgjzM="
                )
            )
            delay(timeMillis = 10000)
            isLoading = false
            isEmpty = false
        }
    }

    fun isEmpty(status: Boolean) {
        isEmpty = status
    }

    fun showError(status: Boolean) {
        showError = status
    }
}