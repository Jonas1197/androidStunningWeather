package com.example.stunningweather.data

import com.example.stunningweather.cache.CacheWeatherData
import com.example.stunningweather.cache.WeatherDataDao
import com.example.stunningweather.models.GeneralForecast

class WeatherDataRepository(
    private val weatherDataDao: WeatherDataDao
): WeatherDataDao {


    override fun insertWeatherData(cachedWeatherData: CacheWeatherData) {
        weatherDataDao.insertWeatherData(cachedWeatherData)
    }

    override fun getWeatherDataForId(id: Int): CacheWeatherData {
        return weatherDataDao.getWeatherDataForId(id)
    }

    override fun getAllWeatherData(): List<CacheWeatherData> {
        return weatherDataDao.getAllWeatherData()
    }

}