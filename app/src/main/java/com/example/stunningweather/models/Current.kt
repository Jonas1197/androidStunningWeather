package com.example.stunningweather.models

class Current(
    val last_updated_epoch: Int = -1,
    val last_updated: String = "NULL",
    val temp_c: Double = -1.0,
    val temp_f: Double = -1.0,
    val is_Day: Int = -1,
    val condition: Condition = Condition(),
    val wind_mph : Double = -1.0,
    val wind_kph : Double = -1.0,
    val wind_degree : Int = -1,
    val wind_dir :  String = "NULL",
    val pressure_mb : Double = -1.0,
    val pressure_in : Double = -1.0,
    val precip_mm : Double = -1.0,
    val precip_in : Double = -1.0,
    val humidity : Int = -1,
    val cloud : Int = -1,
    val feelslike_c : Double = -1.0,
    val feelslike_f : Double = -1.0,
    val vis_km : Double = -1.0,
    val vis_miles : Double = -1.0,
    val uv : Double = -1.0,
    val gust_mph : Double = -1.0,
    val gust_kph : Double = -1.0
)