package com.mostafamohammed.presentation.factory

import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.models.TimedForecast
import com.mostafamohammed.presentation.model.ForecastAttributesView
import com.mostafamohammed.presentation.model.RawWeatherView
import com.mostafamohammed.presentation.model.TimedForecastView

object RawWeatherFactory {
    fun makeRawWeatherView(): RawWeatherView {
        return RawWeatherView(
            timedForecasts = listOf(
                TimedForecastView(
                    attrs = ForecastAttributesView(
                        temp = DataFactory.randomDouble(),
                        tempMin = DataFactory.randomDouble(),
                        tempMax = DataFactory.randomDouble(),
                        pressure = DataFactory.randomDouble()
                    ),
                    date = DataFactory.randomString()
                )
            )
        )
    }

    fun makeRawWeather(): RawWeather {
        return RawWeather(
            timedForecasts = listOf(
                TimedForecast(
                    attrs = ForecastAttributes(
                        temp = DataFactory.randomDouble(),
                        tempMin = DataFactory.randomDouble(),
                        tempMax = DataFactory.randomDouble(),
                        pressure = DataFactory.randomDouble()
                    ),
                    date = DataFactory.randomString()
                )
            )
        )
    }

    fun makeTimedForecastsView(): TimedForecastView {
        return TimedForecastView(
            attrs = ForecastAttributesView(
                temp = DataFactory.randomDouble(),
                tempMin = DataFactory.randomDouble(),
                tempMax = DataFactory.randomDouble(),
                pressure = DataFactory.randomDouble()
            ),
            date = DataFactory.randomString()
        )

    }

    fun makeTimedForecast(): TimedForecast {
        return TimedForecast(
            attrs = ForecastAttributes(
                temp = DataFactory.randomDouble(),
                tempMin = DataFactory.randomDouble(),
                tempMax = DataFactory.randomDouble(),
                pressure = DataFactory.randomDouble()
            ),
            date = DataFactory.randomString()
        )
    }

    fun makeForecastAttributesView(): ForecastAttributesView {
        return  ForecastAttributesView(
                temp = DataFactory.randomDouble(),
                tempMin = DataFactory.randomDouble(),
                tempMax = DataFactory.randomDouble(),
                pressure = DataFactory.randomDouble()
            )
    }

    fun makeForecastAttributes(): ForecastAttributes {
        return ForecastAttributes(
                temp = DataFactory.randomDouble(),
                tempMin = DataFactory.randomDouble(),
                tempMax = DataFactory.randomDouble(),
                pressure = DataFactory.randomDouble()
            )
    }
}