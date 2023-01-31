package com.example.stunningweather.ui

import androidx.compose.ui.graphics.Color

class ColorConstants {
    companion object {
        val DarkOrange = Color(0xFFEB9E7C) // Top color for Evening
        val DeepBlue = Color(0xFF5467C7) // Bottom color Evening
        val BrightRed = Color(0xFFF4BDBB) // Top color for Morning
        val BlueSky = Color(0xFF3BA3DB) // Bottom color for Morning and Day
        val BrightYellow = Color(0xFFEBC992) // Top color for Day
        val NiceGreen = Color(0xFF1F8123)

        val morningGradient = listOf<Color>(
            BrightRed, BlueSky
        )

        val dayGradient = listOf<Color>(
            BrightYellow, BlueSky
        )

        val eveningGradient = listOf<Color>(
            DarkOrange, DeepBlue
        )
    }
}