package com.mostafamohammed.domain.useCases

import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.models.TimedForecast
import com.mostafamohammed.domain.usecases.checkForecast.GetForecast
import kotlin.random.Random

object ForecastDataFactory {
    val rawWeather = RawWeather(
        timedForecasts = provideTimedForecastsList()
    )

    private fun provideTimedForecastsList(): List<TimedForecast> {
        val forecasts = mutableListOf<TimedForecast>()
        repeat(2) {
            forecasts.add(
                TimedForecast(
                    attrs = provideAttrs(),
                    date = provideDateString()
                )
            )
        }
        return forecasts
    }

    private fun provideAttrs(): ForecastAttributes {
        return ForecastAttributes(
            temp = provideAttribute(),
            tempMin = provideAttribute(),
            tempMax = provideAttribute(),
            pressure = provideAttribute()
        )
    }

    private fun provideAttribute(): Double {
        return Random.nextDouble(0.0, 1000.1 / 2.0)
    }

    private fun provideDateString(): String {
        return "25-11-1985"
    }

    fun makeARawWaether(): RawWeather{
       return rawWeather
    }

    fun makeAParam(): GetForecast.Params{
        return GetForecast.Params.createParams("metric","apiKey")
    }

}