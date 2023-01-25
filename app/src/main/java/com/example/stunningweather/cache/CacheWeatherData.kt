package com.example.stunningweather.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stunningweather.models.GeneralForecast
import javax.annotation.Nonnull

@Entity(tableName = "saved_data")
class CacheWeatherData {

    @PrimaryKey(autoGenerate = true)
    @Nonnull
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "weatherData")
    var data: GeneralForecast = GeneralForecast()

    constructor(
        id: Int,
        data: GeneralForecast
    ) {
        this.id = id
        this.data = data
    }
}