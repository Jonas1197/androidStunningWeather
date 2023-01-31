package com.example.stunningweather.usecases.api_service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.stunningweather.models.ErrorMessage
import com.example.stunningweather.models.GeneralForecast
import com.example.stunningweather.network.ApiService
import javax.inject.Inject

class FetchWeatherDataUseCase @Inject constructor(
    override val dataSource: ApiService
): ApiGeneralUseCase<ApiService, GeneralForecast> {

    override suspend fun invoke(
        apiKey: String,
        parameter: String
    ): Either<ErrorMessage, GeneralForecast> {
        val data = dataSource.fetchWeatherData(apiKey, parameter)

        return when {
            data != null -> data.right()
            else -> ErrorMessage("Weather data was not fetched.").left()
        }
    }
}