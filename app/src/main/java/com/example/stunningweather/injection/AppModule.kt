package com.example.stunningweather.injection

import android.app.Application
import android.content.Context
import com.example.stunningweather.cache.WeatherDataDao
import com.example.stunningweather.cache.WeatherDataDatabase
import com.example.stunningweather.data.WeatherDataRepository
import com.example.stunningweather.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideRepository(context: Application): WeatherDataRepository {
        return WeatherDataRepository(
            WeatherDataDatabase.getInstance(
                context
            ).weatherDataDao()
        )
    }
}