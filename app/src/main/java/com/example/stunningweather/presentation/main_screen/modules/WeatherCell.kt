package com.example.stunningweather.presentation.main_screen.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherCell(
    timestamp: Int? = null,
    title: String? = null,
    weather: Double = 0.0,
    isHorizontal: Boolean = false
) {

    Box(
        modifier = Modifier
            .padding(
                top = 8.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp
            )
    ) {

        if (!isHorizontal) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                var titleStr = ""

                titleStr = if(timestamp != null) {
                    val timestamp = timestamp * 1000
                    val dateFormat = SimpleDateFormat("EEEE", Locale.GERMAN)
                    val date = Date(timestamp.toLong())
                    dateFormat.format(date)
                } else
                    title ?: "TITLE"

                Text(
                    text = titleStr,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = "$weather°",
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

        } else {

            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                var titleStr = ""

                titleStr = if(timestamp != null) {
                    val timestamp = timestamp * 1000
                    val dateFormat = SimpleDateFormat("EEEE", Locale.GERMAN)
                    val date = Date(timestamp.toLong())
                    dateFormat.format(date)
                } else
                    title ?: "TITLE"

                Text(
                    text = titleStr,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier
                    .weight(1f)
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = "$weather°",
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}