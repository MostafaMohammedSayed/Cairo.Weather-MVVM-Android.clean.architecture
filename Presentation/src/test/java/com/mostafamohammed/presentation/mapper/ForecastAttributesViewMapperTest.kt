package com.mostafamohammed.presentation.mapper

import com.mostafamohammed.presentation.factory.RawWeatherFactory
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ForecastAttributesViewMapperTest{
    private val mapper = ForecastAttributesViewMapper()

    @Test
    fun mapToViewMapsData(){
        val forecastAttributes = RawWeatherFactory.makeForecastAttributes()
        val forecastAttributesView = mapper.mapToView(forecastAttributes)

        assertEquals(forecastAttributes.temp,forecastAttributesView.temp)
        assertEquals(forecastAttributes.tempMin,forecastAttributesView.tempMin)
        assertEquals(forecastAttributes.tempMax,forecastAttributesView.tempMax)
        assertEquals(forecastAttributes.pressure,forecastAttributesView.pressure)
    }
}