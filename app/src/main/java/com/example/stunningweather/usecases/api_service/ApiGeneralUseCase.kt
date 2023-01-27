package com.example.stunningweather.usecases.api_service

import arrow.core.Either
import com.example.stunningweather.models.ErrorMessage

interface ApiGeneralUseCase<T, R> {
    val dataSource: T
    suspend fun invoke(apiKey: String, coordinates: String): Either<ErrorMessage, R>
}