package com.example.stunningweather.usecases.api_service

import com.example.stunningweather.models.GeneralForecast
import com.example.stunningweather.network.ApiService
import javax.inject.Inject

class FetchWeatherDataUseCaseApi @Inject constructor(
    override val dataSource: ApiService
): ApiGeneralUseCase<ApiService, GeneralForecast> {

    override suspend fun invoke(apiKey: String, coordinates: String): GeneralForecast {
        return dataSource.fetchWeatherData(
            apiKey,
            coordinates
        )
    }
}