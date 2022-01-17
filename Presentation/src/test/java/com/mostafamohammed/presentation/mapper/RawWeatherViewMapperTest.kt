package com.mostafamohammed.presentation.mapper

import com.mostafamohammed.presentation.factory.RawWeatherFactory
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RawWeatherViewMapperTest {
    private val mapper =
        RawWeatherViewMapper(TimedForecastViewMapper(ForecastAttributesViewMapper()))

    @Test
    fun mapToViewMapsData() {
        val rawWeather = RawWeatherFactory.makeRawWeather()
        val rawWeatherView = mapper.mapToView(rawWeather)

        assertEquals(rawWeather.timedForecasts[0].date,rawWeatherView.timedForecasts[0].date)
    }
}