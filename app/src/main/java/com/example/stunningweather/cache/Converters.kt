package com.example.stunningweather.cache

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.stunningweather.models.GeneralForecast
import com.example.stunningweather.parser.GsonParser
import com.example.stunningweather.parser.JsonParser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

//@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser = GsonParser(gson = Gson())
) {


    @TypeConverter
    fun generalWeatherToStringJson(generalForecast: GeneralForecast): String {
        val obj = object : TypeToken<GeneralForecast>(){}

        return jsonParser.toJson(
            generalForecast,
            obj.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromJson(json: String): GeneralForecast {
        val obj = object  : TypeToken<GeneralForecast>(){}

        return jsonParser.fromJson<GeneralForecast>(
            json,
            obj.type
        ) ?: GeneralForecast()
    }
}