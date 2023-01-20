package com.example.stunningweather.models

class ForecastWeather(
    val location: Location = Location(),
    val current: Current = Current(),
    val forecast: Forecast
)