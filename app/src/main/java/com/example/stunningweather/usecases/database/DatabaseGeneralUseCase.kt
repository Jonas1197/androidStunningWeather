package com.example.stunningweather.usecases.database

interface DatabaseGeneralUseCase<T, R> {
    val dataSource: T

    suspend fun <P> invoke(arguments: Map<String,P>?): R?
}