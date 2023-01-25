package com.example.stunningweather.presentation.main_screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.GeneralConstants
import com.example.stunningweather.models.GeneralForecast
import com.example.stunningweather.ui.ColorConstants
import com.example.stunningweather.usecases.api_service.FetchWeatherDataUseCaseApi
import com.example.stunningweather.usecases.database.SaveCurrentWeatherDataUseCase
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val fetchWeatherData: FetchWeatherDataUseCaseApi,
    private val saveDataUseCase: SaveCurrentWeatherDataUseCase
): ViewModel() {

    var state = MainScreenStateObject()

    @SuppressLint("MissingPermission")
    fun fetchWeatherForCurrentLocation(context: Context) {
        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val coordinates = "${it.latitude},${it.longitude}"

                viewModelScope.launch(Dispatchers.IO) {

                    val weatherData = fetchWeatherData.invoke(
                        GeneralConstants.apiKey,
                        coordinates
                    )

                    state.generalForecast.value = weatherData
                    state.didFetchWeather = true

                    saveFetchedWeatherData(weatherData)
                }
            }
        }
    }

    private suspend fun saveFetchedWeatherData(data: GeneralForecast) {
        val arguments = mapOf(GeneralConstants.dataToSave to data)
        saveDataUseCase.invoke(arguments)
    }

    fun weatherThemeBasedOnDayTime(): List<Color> {

        val currentLocale = Locale.getDefault()

        val timeMorning = SimpleDateFormat(
            GeneralConstants.timeFormat,
            currentLocale
        ).parse(GeneralConstants.morningTime)

        val timeDay = SimpleDateFormat(
            GeneralConstants.timeFormat,
            currentLocale
        ).parse(GeneralConstants.dayTime)

        val timeEvening = SimpleDateFormat(
            GeneralConstants.timeFormat,
            currentLocale
        ).parse(GeneralConstants.eveningTime)

        val current = Calendar.getInstance()

        val currentTime = SimpleDateFormat(
            GeneralConstants.timeFormat,
            currentLocale
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