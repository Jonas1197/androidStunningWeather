package com.example.stunningweather.presentation.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.GeneralConstants
import com.example.stunningweather.navigation.Screen
import com.example.stunningweather.presentation.NetworkUtils
import com.example.stunningweather.presentation.PermissionRequesters
import com.example.stunningweather.presentation.main_screen.elements.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

@OptIn(
    ExperimentalPermissionsApi::class,
    ExperimentalMaterialApi::class
)

@SuppressLint(
    "CoroutineCreationDuringComposition",
    "UnusedMaterialScaffoldPaddingParameter"
)

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navigationCallback: (Screen) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.Expanded },
        skipHalfExpanded = true
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            Column(
                Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { coroutineScope.launch { modalSheetState.hide() } }) {
                    Text("Hide sheet")
                }

                Spacer(modifier = Modifier
                    .height(68.dp)
                )

                Button(onClick = { coroutineScope.launch { modalSheetState.hide() } }) {
                    Text("Hide sheet")
                }
            }
        }
    ) {
        Scaffold {
            Box(
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0f))
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

//        Button(onClick = {
//            viewModel.viewModelScope.launch(Dispatchers.IO) {
//                viewModel.fetchSavedData()
//            }
//        }) {
//            Text(text = "Fetch saved data")
//        }

                    // Add Location Button
                    ButtonWithSymbol {
                        coroutineScope.launch {
                            if (modalSheetState.isVisible)
                                modalSheetState.hide()
                            else
                                modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    }

                    if (!NetworkUtils.isOnline()) {
                        Column(
                            modifier = Modifier
                                .padding(60.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Spacer(Modifier.height(160.dp))

                            Text(
                                text = GeneralConstants.noConnectionText,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                fontStyle = FontStyle.Normal,
                                color = Color.White
                            )

                            Spacer(Modifier.height(18.dp))

                            Image(
                                modifier = Modifier
                                    .padding(48.dp),
                                painter = painterResource(GeneralConstants.noConnectionImage),
                                contentDescription = null
                            )
                        }

                    } else {
                        val context = LocalContext.current
                        if (!viewModel.state.didFetchWeather) {
                            PermissionRequesters.CheckLocationPermission(
                                context = LocalContext.current,
                                locationRequestHandler = {
                                    MainScope().launch { it.launchPermissionRequest() }

                                }) { viewModel.fetchWeatherForCurrentLocation(context) }
                        }


                        // Current weather
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
                            if (viewModel.state.generalForecast.value.forecast.forecastday.isNotEmpty()) {
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
                            if (viewModel.state.generalForecast.value.forecast.forecastday.isNotEmpty())
                                ScrollComponent(
                                    items = viewModel.state.generalForecast.value.forecast.forecastday,
                                    isHorizontal = false,
                                    fillsMaxHeight = true
                                ) { _, item ->
                                    WeatherCell(
                                        timestamp = item.date_epoch,
                                        weather = item.day.maxtemp_c,
                                        isHorizontal = true
                                    )
                                }
                        }
                    }
                }
            }
        }
    }
}