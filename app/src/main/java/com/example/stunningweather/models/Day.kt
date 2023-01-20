package com.example.stunningweather.models

class Day(
    val maxtemp_c: Double = -1.0,
    val maxtemp_f: Double = -1.0,
    val mintemp_c: Double = -1.0,
    val mintemp_f: Double = -1.0,
    val avgtemp_c: Double = -1.0,
    val avgtemp_f: Double = -1.0,
    val maxwind_mph: Double = -1.0,
    val maxwind_kph: Double = -1.0,
    val totalprecip_mm: Double = -1.0,
    val totalprecip_in: Double = -1.0,
    val totalsnow_cm: Double = -1.0,
    val avgvis_km: Double = -1.0,
    val avgvis_miles: Double = -1.0,
    val avghumidity: Int = -1,
    val daily_will_it_rain: Int = -1,
    val daily_chance_of_rain: Int = -1,
    val daily_will_it_snow: Int = -1,
    val daily_chance_of_snow: Int = -1,
    val condition: Condition = Condition(),
    val uv: Int = -1
)