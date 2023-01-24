package com.example.stunningweather.models

import androidx.room.Entity

class GeneralForecast(
    val location: Location = Location(),
    val current: Current = Current(),
    val forecast: Forecast = Forecast()
)