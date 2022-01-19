package com.mostafamohammed.presentation.mapper

import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.models.TimedForecast
import com.mostafamohammed.presentation.factory.RawWeatherFactory
import com.mostafamohammed.presentation.model.ForecastAttributesView
import com.mostafamohammed.presentation.model.RawWeatherView
import com.mostafamohammed.presentation.model.TimedForecastView
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class RawWeatherViewMapperTest {
    private lateinit var mapper: RawWeatherViewMapper
    @Mock private lateinit var timedForecastViewMapper: TimedForecastViewMapper

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        mapper = RawWeatherViewMapper(timedForecastViewMapper)
    }

    private fun stubTimedForecastViewMapperMapToView(model: TimedForecast) {
        whenever(timedForecastViewMapper.mapToView(model))
            .thenReturn(TimedForecastView(
                attrs = ForecastAttributesView(
                    temp = model.attrs.temp,
                    tempMax = model.attrs.tempMax,
                    tempMin = model.attrs.tempMin,
                    pressure = model.attrs.pressure
                ),
                date = model.date
            ))
    }

    @Test
    fun mapToViewMapsData() {
        val rawWeather = RawWeatherFactory.makeRawWeather()
        rawWeather.timedForecasts.map { timedForecast ->
            stubTimedForecastViewMapperMapToView(timedForecast)
        }
        val rawWeatherView = mapper.mapToView(rawWeather)
        assertEqualData(rawWeather,rawWeatherView)
    }

    private fun assertEqualData(rawWeather: RawWeather, rawWeatherView: RawWeatherView) {
        val timedForecasts = rawWeather.timedForecasts
        val timedForecastViews = rawWeatherView.timedForecasts

        for (i in timedForecasts.indices){
            assert(timedForecasts[i].date == timedForecastViews[i].date)
            assert(timedForecasts[i].attrs.temp == timedForecastViews[i].attrs.temp)
            assert(timedForecasts[i].attrs.tempMin == timedForecastViews[i].attrs.tempMin)
            assert(timedForecasts[i].attrs.tempMax == timedForecastViews[i].attrs.tempMax)
            assert(timedForecasts[i].attrs.pressure == timedForecastViews[i].attrs.pressure)
        }
    }
}