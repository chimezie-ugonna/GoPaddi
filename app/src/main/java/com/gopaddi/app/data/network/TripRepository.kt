package com.gopaddi.app.data.network

import android.content.Context
import com.google.gson.Gson
import com.gopaddi.app.R
import com.gopaddi.app.data.TripData
import com.gopaddi.app.utils.ApiClientUtil
import com.gopaddi.app.utils.EchoApiClientUtil
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TripRepository {

    fun getTrips(context: Context, callback: (Result<List<TripData>>) -> Unit) {
        ApiClientUtil.tripApiService.getTrips().enqueue(object : Callback<List<TripData>> {
            override fun onResponse(
                call: Call<List<TripData>>, response: Response<List<TripData>>
            ) {
                if (response.isSuccessful) {
                    callback(Result.success(response.body() ?: emptyList()))
                } else {
                    val responseBody = response.errorBody()?.string() ?: ""
                    callback(
                        Result.failure(
                            Throwable(
                                context.getString(
                                    R.string.error, when {
                                        response.message().isNotEmpty() -> "${response.message()}"
                                        responseBody.isNotEmpty() -> responseBody
                                        else -> context.getString(R.string.an_unknown_error_occurred)
                                    }
                                )
                            )
                        )
                    )
                }
            }

            override fun onFailure(call: Call<List<TripData>>, t: Throwable) {
                callback(Result.failure(t))
            }
        })
    }

    fun createTrip(context: Context, tripData: TripData, callback: (Result<TripData>) -> Unit) {
        EchoApiClientUtil.tripApiService.createTrip(tripData)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>, response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.string()?.let { responseJson ->
                            try {
                                val parsedData =
                                    JSONObject(responseJson).getJSONObject("parsedBody")
                                val tripData =
                                    Gson().fromJson(parsedData.toString(), TripData::class.java)
                                callback(Result.success(tripData))
                            } catch (e: Exception) {
                                callback(
                                    Result.failure(
                                        Throwable(
                                            context.getString(
                                                R.string.failed_to_parse_response, e.message
                                            )
                                        )
                                    )
                                )
                            }
                        } ?: run {
                            callback(Result.failure(Throwable(context.getString(R.string.empty_response_body))))
                        }
                    } else {
                        val errorBody = response.errorBody()?.string() ?: context.getString(
                            R.string.an_unknown_error_occurred
                        )
                        callback(
                            Result.failure(
                                Throwable(
                                    context.getString(
                                        R.string.error, errorBody
                                    )
                                )
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    callback(Result.failure(t))
                }
            })
    }
}