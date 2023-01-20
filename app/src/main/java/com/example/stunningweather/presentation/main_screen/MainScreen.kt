package com.example.stunningweather.presentation.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stunningweather.navigation.Screen
import com.example.stunningweather.presentation.NetworkUtils
import com.example.stunningweather.presentation.main_screen.modules.MainWeatherModule
import com.example.stunningweather.presentation.main_screen.modules.WeatherCell
import com.example.stunningweather.presentation.main_screen.modules.ScrollComponent
import com.example.stunningweather.presentation.PermissionRequesters
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.example.stunningweather.R
import com.example.stunningweather.ui.ColorConstants
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    navigationCallback: (Screen) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    viewModel.weatherThemeBasedOnDayTime()
                )
            )
    ) {

        if(!NetworkUtils.isOnline()) {
            Column(
                modifier = Modifier
                    .padding(60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(
                    Modifier
                        .height(160.dp)
                )

                Text(
                    text = "Wops! Looks like your internet connection is gone!",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    color = Color.White
                )

                Spacer(
                    Modifier
                        .height(18.dp)
                )

                Image(
                    modifier = Modifier
                        .padding(48.dp),
                    painter = painterResource(R.drawable.noconnection),
                    contentDescription = null)
            }

        } else {
            val context = LocalContext.current
            if(!viewModel.state.didFetchWeather) {
                PermissionRequesters.CheckLocationPermission(
                    context = LocalContext.current,
                    locationRequestHandler = {
                        MainScope().launch { it.launchPermissionRequest() }
                    }) {
                    viewModel.fetchWeatherForCurrnetLocation(context)
                }
            }


//         Current weather
            Column {
                MainWeatherModule(
                    name = viewModel.state.generalForecast.value.location.name,
                    country = viewModel.state.generalForecast.value.location.country,
                    weather = viewModel.state.generalForecast.value.current.temp_c.toInt(),
                    description = viewModel.state.generalForecast.value.current.condition.text,
                    feelsLike = viewModel.state.generalForecast.value.current.feelslike_c.toInt(),
                    humidity = viewModel.state.generalForecast.value.current.humidity
                )


                // Hourly forecast
                if(viewModel.state.generalForecast.value.forecast.forecastday.isNotEmpty()) {
                    ScrollComponent(
                        items = viewModel.state.generalForecast.value.forecast.forecastday.first().hour,
                        height = 150.dp
                    ) { _, item ->

                        WeatherCell(
                            title = item.time.substringAfter(' '),
                            weather = item.temp_c
                        )
                    }
                }


                // Daily forecast
                if(viewModel.state.generalForecast.value.forecast.forecastday.isNotEmpty()) {
                    ScrollComponent(
                        items = viewModel.state.generalForecast.value.forecast.forecastday,
                        isHorizontal = false,
                        fillsMaxHeight = true
                    ) { _, item ->
                        val timestamp = item.date_epoch * 1000
                        val dateFormat = SimpleDateFormat("EEEE", Locale.GERMAN)
                        val date = Date(timestamp.toLong())
                        val dayString = dateFormat.format(date)

                        WeatherCell(
                            title = dayString,
                            weather = item.day.maxtemp_c,
                            isHorizontal = true
                        )
                    }
                }
            }
        }
    }
}