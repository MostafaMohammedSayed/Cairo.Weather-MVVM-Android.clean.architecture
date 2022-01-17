package com.mostafamohammed.presentation.mapper

import com.mostafamohammed.presentation.factory.RawWeatherFactory
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TimedForecastViewMapperTest {
    private val mapper = TimedForecastViewMapper(ForecastAttributesViewMapper())

    @Test
    fun mapToViewMapsData() {
        val timedForecast = RawWeatherFactory.makeTimedForecast()
        val timedForecastView = mapper.mapToView(timedForecast)

        assertEquals(timedForecast.date, timedForecastView.date)
        assertEquals(timedForecast.attrs.temp,timedForecastView.attrs.temp,)
        assertEquals(timedForecast.attrs.tempMin,timedForecastView.attrs.tempMin)
        assertEquals(timedForecast.attrs.tempMax,timedForecastView.attrs.tempMax)
        assertEquals(timedForecast.attrs.pressure,timedForecastView.attrs.pressure)
    }
}