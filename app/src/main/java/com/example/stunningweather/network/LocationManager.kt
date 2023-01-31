package com.example.stunningweather.network

import android.location.Address
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class LocationManager {

    companion object {

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        @Composable
        fun fetchCoordinatesForLocationName(name: String, completion: (List<Address>) -> Unit) {
            val gc = Geocoder(LocalContext.current)
            gc.getFromLocationName(name, 5, Geocoder.GeocodeListener {
                completion(it)
            })
        }
    }
}