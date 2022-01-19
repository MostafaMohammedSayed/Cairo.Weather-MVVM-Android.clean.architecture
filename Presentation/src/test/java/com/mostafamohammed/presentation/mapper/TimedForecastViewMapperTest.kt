package com.mostafamohammed.presentation.mapper

import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.presentation.factory.RawWeatherFactory
import com.mostafamohammed.presentation.model.ForecastAttributesView
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class TimedForecastViewMapperTest {
    private lateinit var mapper: TimedForecastViewMapper
    @Mock
    private lateinit var forecastAttributesViewMapper: ForecastAttributesViewMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mapper = TimedForecastViewMapper(forecastAttributesViewMapper)
    }

    private fun stubForecastAttributesViewMapperMapToView(model: ForecastAttributes) {
        whenever(forecastAttributesViewMapper.mapToView(model))
            .thenReturn(
                ForecastAttributesView(
                    temp = model.temp,
                    tempMin = model.tempMin,
                    tempMax = model.tempMax,
                    pressure = model.pressure
                )
            )
    }

    @Test
    fun mapToViewMapsData() {
        val timedForecast = RawWeatherFactory.makeTimedForecast()
        stubForecastAttributesViewMapperMapToView(timedForecast.attrs)
        val timedForecastView = mapper.mapToView(timedForecast)

        assertEquals(timedForecast.date, timedForecastView.date)
        assertEquals(timedForecast.attrs.temp, timedForecastView.attrs.temp)
        assertEquals(timedForecast.attrs.tempMin, timedForecastView.attrs.tempMin)
        assertEquals(timedForecast.attrs.tempMax, timedForecastView.attrs.tempMax)
        assertEquals(timedForecast.attrs.pressure, timedForecastView.attrs.pressure)
    }
}