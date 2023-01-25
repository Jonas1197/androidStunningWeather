package com.example.stunningweather.usecases.api_service

interface ApiGeneralUseCase<T, R> {
    val dataSource: T
    suspend fun invoke(apiKey: String, coordinates: String): R
}