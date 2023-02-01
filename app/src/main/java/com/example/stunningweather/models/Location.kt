package com.example.stunningweather.models

class Location(
    val name: String = "NULL",
    val region: String = "NULL" ,
    val country: String = "NULL" ,
    val lat: Double = -1.0,
    val lon: Double = -1.0,
    val tz_id : String = "NULL",
    val localtime_epoch: Int = -1,
    val localtime : String = "NULL"
)