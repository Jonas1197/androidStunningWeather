package com.example.stunningweather.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class PermissionRequesters {

    companion object {

        @SuppressLint("CoroutineCreationDuringComposition")
        @OptIn(ExperimentalPermissionsApi::class)
        @Composable
        fun CheckLocationPermission(context: Context,
                                    locationRequestHandler: (PermissionState) -> Unit,
        shouldUpdateWeather: () -> Unit) {

            val locationPermission = rememberPermissionState(
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )

            println("Status ${locationPermission.status}")

            if(ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
                shouldUpdateWeather()
             else
                locationRequestHandler(locationPermission)
        }
    }
}