package com.example.stunningweather.cache

import androidx.room.*

@Dao
interface WeatherDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherData(cachedWeatherData: CacheWeatherData)

    @Query("SELECT * FROM saved_data WHERE id = :id")
    fun getWeatherDataForId(id: Int): CacheWeatherData

    @Query("SELECT * FROM saved_data")
    fun getAllWeatherData(): List<CacheWeatherData>

}