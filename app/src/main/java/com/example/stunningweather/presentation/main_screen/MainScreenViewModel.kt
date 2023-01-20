package com.example.stunningweather.presentation.main_screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stunningweather.network.ApiService
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {

    var state = MainScreenStateObject()

    @SuppressLint("MissingPermission")
    fun fetchWeatherForCurrnetLocation(context: Context) {

        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val coordinates = "${it.latitude},${it.longitude}"


                //This should be a use case ------------
                viewModelScope.launch(Dispatchers.IO) {
                    state.generalForecast.value = apiService
                        .fetchWeatherData(
                            apiKey = "94d3917c681b4dba821100254231201",
                            coordinates = coordinates
                        )

                    state.didFetchWeather = true
                }
                //-------------------------------



            }
        }
    }
}