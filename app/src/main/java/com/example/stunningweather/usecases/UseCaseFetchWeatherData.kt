package com.example.stunningweather.usecases

import com.example.stunningweather.models.GeneralForecast
import com.example.stunningweather.network.ApiService

abstract class UseCaseFetchWeatherData(
    dataSource: ApiService
): GeneralUseCase<ApiService, GeneralForecast> {
    override val dataSource: ApiService = dataSource

    override suspend fun invoke(apiKey: String): GeneralForecast {
        return dataSource.fetchWeatherData(apiKey)
    }
}