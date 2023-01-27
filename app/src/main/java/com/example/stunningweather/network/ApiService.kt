package com.example.stunningweather.network

import com.example.stunningweather.models.GeneralForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
    suspend fun fetchWeatherData(
        @Query("key") apiKey: String,
        @Query("q") coordinates: String = "48.1351,11.5820",
        @Query("days") days: Int = 7
    ): GeneralForecast?
}