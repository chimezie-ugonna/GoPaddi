package com.gopaddi.app.utils

import com.gopaddi.app.data.network.TripApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientUtil {
    private const val BASE_URL = "https://gopaddiapi.free.beeceptor.com"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val tripApiService: TripApiService = retrofit.create(TripApiService::class.java)
}