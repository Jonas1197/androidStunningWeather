package com.example.stunningweather.models

class ForecastDay(
    val date: String = "NULL",
    val date_epoch: Int = -1,
    val day: Day = Day(),
    val astro: Astro = Astro(),
    val hour: List<Hour> = emptyList()
)