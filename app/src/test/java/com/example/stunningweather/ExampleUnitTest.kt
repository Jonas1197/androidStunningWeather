package com.example.stunningweather

import evalBash
import org.junit.Test

import org.junit.Assert.*
import runCommand

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun runMaestroFlow() {
        val bashResult = "maestro test flow.yaml".evalBash()

        bashResult.stdout.forEach {
            println("~~> Assertion for str: $it")
            assert(!it.contains("FAILED"))
        }
    }
}