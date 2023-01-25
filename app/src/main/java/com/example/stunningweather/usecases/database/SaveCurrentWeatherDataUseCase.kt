package com.example.stunningweather.usecases.database

import com.example.GeneralConstants
import com.example.stunningweather.cache.CacheWeatherData
import com.example.stunningweather.data.WeatherDataRepository
import com.example.stunningweather.models.GeneralForecast
import javax.inject.Inject

class SaveCurrentWeatherDataUseCase @Inject constructor(
    override val dataSource: WeatherDataRepository
 ) : DatabaseGeneralUseCase<WeatherDataRepository, Unit> {

    override suspend fun <P> invoke(arguments: Map<String, P>?) {

        val data = arguments?.get(GeneralConstants.dataToSave) as? GeneralForecast ?: return

        dataSource.insertWeatherData(
            CacheWeatherData(
                id = 0,
                data = data
            )
        )
    }
 }