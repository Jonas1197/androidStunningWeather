package com.example.stunningweather.usecases

interface GeneralUseCase<T, R> {
    val dataSource: T
    suspend fun invoke(apiKey: String): R
}