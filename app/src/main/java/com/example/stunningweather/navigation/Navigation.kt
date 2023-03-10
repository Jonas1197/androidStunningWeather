package com.example.stunningweather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stunningweather.network.ApiService
import com.example.stunningweather.presentation.main_screen.MainScreen
import com.example.stunningweather.presentation.main_screen.MainScreenViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {

        composable(Screen.MainScreen.route) {
            MainScreen(
                navigationCallback = { screen ->
                    navController.navigate(screen.route)
                }
            )
        }
    }
}