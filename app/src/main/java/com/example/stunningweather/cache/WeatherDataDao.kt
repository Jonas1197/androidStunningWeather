package com.example.stunningweather.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDataDao {

    @Insert
    fun insertWeatherData(cachedWeatherData: CacheWeatherData)

    @Query("SELECT * FROM saved_data WHERE id = :id")
    fun getWeatherDataForId(id: Int): CacheWeatherData

}