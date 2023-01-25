package com.example.stunningweather.usecases

import com.example.stunningweather.models.GeneralForecast
import com.example.stunningweather.network.ApiService
import javax.inject.Inject

class FetchWeatherDataUseCase @Inject constructor(
    dataSource: ApiService
): GeneralUseCase<ApiService, GeneralForecast> {
    override val dataSource: ApiService = dataSource

    override suspend fun invoke(apiKey: String, coordinates: String): GeneralForecast {
        return dataSource.fetchWeatherData(
            apiKey,
            coordinates
        )
    }
}