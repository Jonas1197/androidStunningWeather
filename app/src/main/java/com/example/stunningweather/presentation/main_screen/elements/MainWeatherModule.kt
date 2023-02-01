package com.example.stunningweather.presentation.main_screen.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainWeatherModule(
    name: String,
    country: String,
    weather: Int,
    feelsLike: Int,
    humidity: Int,
    description: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Location name
        Text(
            modifier = Modifier
                .semantics {
                    testTagsAsResourceId = true
                    testTag = "LocationNameLabel"
                }
                .padding(top = 54.dp),
            text = name,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            fontSize = 32.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        // Country name
        Text(
            modifier = Modifier
                .semantics {
                    testTagsAsResourceId = true
                    testTag = "CountryNameLabel"
                }
                .padding(top = 8.dp),
            text = country,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        // Big weather text
        Text(
            modifier = Modifier
                .semantics {
                    testTagsAsResourceId = true
                    testTag = "BigWeatherLabel"

                }
                .padding(top = 16.dp),
            text = "$weather°",
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            fontSize = 68.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        // Weather description
        Text(
            modifier = Modifier
                .semantics {
                    testTagsAsResourceId = true
                    testTag = "WeatherDescriptionLabel"
                }
                .padding(top = 8.dp),
            text = description,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Feels like temp
            Text(
                modifier = Modifier
                    .semantics {
                        testTagsAsResourceId = true
                        testTag = "FeelsLikeLabel"
                    }
                    .padding(top = 8.dp),
                text = "Feels like: $feelsLike°",
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(
                Modifier
                    .width(16.dp)
            )

            // Humidity
            Text(
                modifier = Modifier
                    .semantics {
                        testTagsAsResourceId = true
                        testTag = "HumidityLabel"
                    }
                    .padding(top = 8.dp),
                text = "Humidity: $humidity%",
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}