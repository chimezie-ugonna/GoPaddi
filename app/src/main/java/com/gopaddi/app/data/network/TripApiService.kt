package com.gopaddi.app.data.network

import com.gopaddi.app.data.TripData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TripApiService {
    @GET("/trips")
    fun getTrips(): Call<List<TripData>>

    @POST("/trip")
    fun createTrip(@Body trip: TripData): Call<ResponseBody>
}