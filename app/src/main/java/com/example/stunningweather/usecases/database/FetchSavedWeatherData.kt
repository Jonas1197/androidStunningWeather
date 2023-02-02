package com.example.stunningweather.usecases.database

import com.example.stunningweather.cache.CacheWeatherData
import com.example.stunningweather.data.WeatherDataRepository
import com.example.stunningweather.models.GeneralForecast
import javax.inject.Inject

class FetchSavedWeatherData @Inject constructor(
    override val dataSource: WeatherDataRepository
) : DatabaseGeneralUseCase<WeatherDataRepository, CacheWeatherData?> {

    override suspend fun <P> invoke(arguments: Map<String, P>?): CacheWeatherData? {
        val data = dataSource.getAllWeatherData()
        return data.lastOrNull()
    }
}