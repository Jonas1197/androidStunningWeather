package com.example.stunningweather.presentation.main_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.stunningweather.models.GeneralForecast

data class MainScreenStateObject(
    var generalForecast: MutableState<GeneralForecast> = mutableStateOf(GeneralForecast()),
    var locationPermissionGranted: MutableState<Boolean> = mutableStateOf(false),
    var didFetchWeather: Boolean = false,
    var didDismissSheet: MutableState<Boolean> = mutableStateOf(false)
)