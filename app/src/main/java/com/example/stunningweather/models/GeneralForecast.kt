package com.example.stunningweather.models

class GeneralForecast(
    val location: Location = Location(),
    val current: Current = Current(),
    val forecast: Forecast = Forecast()
)