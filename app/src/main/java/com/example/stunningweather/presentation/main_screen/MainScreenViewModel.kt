package com.example.stunningweather.presentation.main_screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stunningweather.ui.ColorConstants
import com.example.stunningweather.usecases.FetchWeatherDataUseCase
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val fetchWeatherData: FetchWeatherDataUseCase
): ViewModel() {

    var state = MainScreenStateObject()

    @SuppressLint("MissingPermission")
    fun fetchWeatherForCurrnetLocation(context: Context) {

        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val coordinates = "${it.latitude},${it.longitude}"

                viewModelScope.launch(Dispatchers.IO) {
                    state.generalForecast.value = fetchWeatherData.invoke(
                        "94d3917c681b4dba821100254231201",
                        coordinates
                    )

                    state.didFetchWeather = true
                }
            }
        }
    }

    fun weatherThemeBasedOnDayTime(): List<Color> {
        val morning = "05:00:00"
        val day = "12:00:00"
        val evening = "17:00:00"

        val timeMorning = SimpleDateFormat(
            "HH:mm:ss",
            Locale.GERMAN
        ).parse(morning)

        val timeDay = SimpleDateFormat(
            "HH:mm:ss",
            Locale.GERMAN
        ).parse(day)

        val timeEvening = SimpleDateFormat(
            "HH:mm:ss",
            Locale.GERMAN
        ).parse(evening)

        val current = Calendar.getInstance()

        val currentTime = SimpleDateFormat(
            "HH:mm:ss",
            Locale.GERMAN
        ).parse("${current.get(Calendar.HOUR_OF_DAY)}:" +
                "${current.get(Calendar.MINUTE)}:" +
                "${current.get(Calendar.SECOND)}") ?: return ColorConstants.dayGradient

        return if(currentTime.after(timeMorning) && currentTime.before(timeDay)) {
            ColorConstants.morningGradient
        } else if(currentTime.after(timeDay) && currentTime.before(timeEvening)) {
            ColorConstants.dayGradient
        } else if(currentTime.after(timeEvening) && currentTime.before(timeMorning)) {
            ColorConstants.eveningGradient
        } else {
            ColorConstants.dayGradient
        }
    }
}