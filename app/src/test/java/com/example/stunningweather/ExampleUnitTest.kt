package com.example.stunningweather

import evalBash
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun runMaestroFlow() {

        // Run the maestro test from the flow.yaml file.
        val bashResult = "maestro test flow.yaml".evalBash()

        // For each test statement, check if failed and assert.
        bashResult.stdout.forEach {
            println("~~> Assertion for str: $it")
            assert(!it.contains("FAILED"))
        }
    }
}