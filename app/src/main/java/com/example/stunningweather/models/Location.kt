package com.example.stunningweather.models

class Location(
     val name: String = "-1",
    val region: String = "-1" ,
    val country: String = "-1" ,
    val lat: Double = -1.0,
    val lon: Double = -1.0,
    val tz_id : String = "-1",
    val localtime_epoch: Int = -1,
    val localtime : String = "-1"
)