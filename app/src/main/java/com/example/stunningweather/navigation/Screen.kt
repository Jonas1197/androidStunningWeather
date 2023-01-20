package com.example.stunningweather.navigation

sealed class Screen(
    val route: String
) {
    object MainScreen : Screen("MainScreen")
}
