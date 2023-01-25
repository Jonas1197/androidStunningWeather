package com.example.stunningweather.usecases.database

import com.example.stunningweather.data.WeatherDataRepository
import com.example.stunningweather.models.GeneralForecast
import javax.inject.Inject

class FetchSavedWeatherData @Inject constructor(
    override val dataSource: WeatherDataRepository
) : DatabaseGeneralUseCase<WeatherDataRepository, GeneralForecast> {

    override suspend fun <P> invoke(arguments: Map<String, P>?): GeneralForecast? {
        return dataSource.getWeatherDataForId(0).data
    }
}