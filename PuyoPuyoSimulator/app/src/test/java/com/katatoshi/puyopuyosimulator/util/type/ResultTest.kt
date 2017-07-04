package com.katatoshi.puyopuyosimulator.util.type

import org.hamcrest.CoreMatchers.`is`
import org.junit.Test
import org.junit.Assert.*

class ResultTest {

    @Test
    fun Okを返す関数でOkをflatMapするとOkである() {
        val result = Result.Ok<Int, String>(2).flatMap { x -> Result.Ok<Float, String>(3f * x) }

        when (result) {
            is Result.Error -> fail()
        }
    }

    @Test
    fun Errorを返す関数でOkをflatMapすると関数の返すErrorの値でErrorである() {
        val result = Result.Ok<Int, String>(2).flatMap { x -> Result.Error<Float, String>("B") }

        when (result) {
            is Result.Ok -> fail()
            is Result.Error -> assertThat(result.error, `is`("B"))
        }
    }

    @Test
    fun Okを返す関数でErrorをflatMapすると最初のErrorの値でErrorである() {
        val result = Result.Error<Int, String>("A").flatMap { x -> Result.Ok<Float, String>(3f * x) }

        when (result) {
            is Result.Ok -> fail()
            is Result.Error -> assertThat(result.error, `is`("A"))
        }
    }

    @Test
    fun Errorを返す関数でErrorをflatMapすると最初のErrorの値でErrorである() {
        val result = Result.Error<Int, String>("A").flatMap { x -> Result.Error<Float, String>("B") }

        when (result) {
            is Result.Ok -> fail()
            is Result.Error -> assertThat(result.error, `is`("A"))
        }
    }
}