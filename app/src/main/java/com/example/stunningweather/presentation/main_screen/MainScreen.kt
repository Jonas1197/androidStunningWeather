package com.example.stunningweather.presentation.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.GeneralConstants
import com.example.stunningweather.models.GeneralForecast
import com.example.stunningweather.navigation.Screen
import com.example.stunningweather.presentation.NetworkUtils
import com.example.stunningweather.presentation.PermissionRequesters
import com.example.stunningweather.presentation.main_screen.elements.*
import com.example.stunningweather.ui.ColorConstants
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.*
import java.util.*

@OptIn(
    ExperimentalPermissionsApi::class,
    ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class, DelicateCoroutinesApi::class
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

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.Expanded },
        skipHalfExpanded = true
    )

    println()
    println()
    println()
    println()
    

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .semantics {
                        testTagsAsResourceId = true
                        testTag = "BottomSheetColumn"
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CustomTextField(
                    modifier = Modifier
                        .padding(32.dp)
                        .semantics {
                            testTagsAsResourceId = true
                            testTag = "NewLocationTextField"
                        },
                    hint = "Location name",
                    shouldClear = viewModel.state.didDismissSheet
                ) {
                    viewModel.newLocationName = it
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.padding(start = 42.dp, end = 42.dp),
                    text = "Enter the coordinates or the name of the location you would like to see the weather data for.",
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .width(200.dp)
                            .padding(32.dp)
                            .semantics {
                                testTagsAsResourceId = true
                                testTag = "DoneSheetButton"
                            },
                        colors = ButtonDefaults.buttonColors(ColorConstants.DeepBlue),
                        onClick = {
                            coroutineScope.launch {
                                focusManager.clearFocus()
                                modalSheetState.hide()
                                viewModel.state.didDismissSheet.value = true
                                viewModel.fetchWeatherForLocationName()
                            }
                        }
                    ) {
                        Text("Done")
                    }

                    Button(
                        modifier = Modifier
                            .width(200.dp)
                            .padding(32.dp)
                            .semantics {
                                testTagsAsResourceId = true
                                testTag = "CancelSheetButton"

                            },
                        colors = ButtonDefaults.buttonColors(ColorConstants.DarkOrange),
                        onClick = {
                            coroutineScope.launch {
                                focusManager.clearFocus()
                                modalSheetState.hide()
                                viewModel.state.didDismissSheet.value = true
                            }
                        }
                    ) {
                        Text("Cancel")
                    }
                }

                Spacer(modifier = Modifier.height(380.dp))

            }
        }
    ) {
        Scaffold {
            Box(
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0f))
                    .pointerInput(Unit) {
                        detectTapGestures { focusManager.clearFocus() }
                    }
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

                    Column {
                        // Add Location Button
                        ButtonWithSymbol(
                            Modifier
                                .padding(start = 32.dp, top = 32.dp)
                                .size(65.dp)
                                .semantics {
                                    testTagsAsResourceId = true
                                    testTag = "AddButton"
                                },
                            symbol = Icons.Rounded.Add,
                            backgroundColor = ColorConstants.DeepBlue,
                            tintColor = Color.White
                        ) {
                            coroutineScope.launch {
                                if (modalSheetState.isVisible)
                                    modalSheetState.hide()
                                else
                                    modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                            }
                        }

                        // Add Location Button
                        ButtonWithSymbol(
                            Modifier
                                .padding(start = 32.dp, top = 16.dp)
                                .size(65.dp)
                                .semantics {
                                    testTagsAsResourceId = true
                                    testTag = "CurrentLocationButton"
                                },
                            symbol = Icons.Rounded.LocationOn,
                            backgroundColor = ColorConstants.NiceGreen,
                            tintColor = Color.White
                        ) {
                            viewModel.state.generalForecast.value = GeneralForecast()
                            viewModel.fetchWeatherForCurrentLocation(context)
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

                        var didFetchSavedData = false

                        runBlocking {
                            GlobalScope.launch(Dispatchers.IO) {
                                didFetchSavedData = viewModel.fetchSavedData()
                            }
                        }

                        if(!didFetchSavedData && !viewModel.state.didFetchWeather) {

                            // For Automated Testing
                            viewModel.newLocationName = "Munich"
                            viewModel.fetchWeatherForLocationName()

                            // Production
//                            PermissionRequesters.CheckLocationPermission(
//                                context = context,
//                                locationRequestHandler = {
//                                    MainScope().launch { it.launchPermissionRequest() }
//                                }) {
//                                viewModel.fetchWeatherForCurrentLocation(context)
//                            }
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