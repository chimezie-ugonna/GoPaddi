package com.gopaddi.app.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.gopaddi.app.R
import com.gopaddi.app.data.TripData
import com.gopaddi.app.data.network.TripRepository

class Screen1ViewModel : ViewModel() {
    val tripName = mutableStateOf(TextFieldValue(""))
    val tripDescription = mutableStateOf(TextFieldValue(""))
    var tripItems by mutableStateOf(emptyList<TripData>())
        private set

    var tripData by mutableStateOf(TripData())
        private set

    var tripIsCreated by mutableStateOf(false)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var hasLoaded by mutableStateOf(false)
        private set

    var isEmpty by mutableStateOf(false)
        private set

    var showError by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    private val tripRepository = TripRepository()

    fun fetchTrips(context: Context) {
        isLoading = true
        tripRepository.getTrips(context = context) { result ->
            isLoading = false
            result.onSuccess { trips ->
                tripItems = trips
                isEmpty = trips.isEmpty()
            }
            result.onFailure { error ->
                showError = true
                errorMessage = error.localizedMessage ?: context.getString(
                    R.string.failed_to_fetch_trips, error.message
                )
            }
        }
    }

    fun createTrip(context: Context, onComplete: () -> Unit) {
        tripRepository.createTrip(context = context, tripData = tripData) { result ->
            onComplete()
            result.onSuccess { createdTrip ->
                addTrip(newTrip = createdTrip)
                tripIsCreated = true
                if (isEmpty) {
                    isEmpty = false
                }
            }
            result.onFailure { error ->
                showError = true
                errorMessage = error.localizedMessage ?: context.getString(
                    R.string.failed_to_create_trip, error.message
                )
            }
        }
    }

    fun showError(status: Boolean) {
        showError = status
    }

    fun tripIsCreated(status: Boolean) {
        tripIsCreated = status
    }

    fun hasLoaded(status: Boolean) {
        hasLoaded = status
    }

    fun addTrip(newTrip: TripData) {
        tripItems = listOf(newTrip) + tripItems
    }

    fun resetTripData() {
        tripData = TripData()
    }

    fun updateTripData(tripName: String, tripDescription: String, travelStyle: String) {
        tripData = tripData.copy(
            tripName = tripName, tripDescription = tripDescription, travelStyle = travelStyle
        )
    }
}