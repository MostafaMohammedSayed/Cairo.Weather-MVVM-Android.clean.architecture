package com.mostafamohammed.presentation.mapper

import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.presentation.model.RawWeatherView

class RawWeatherViewMapper(private val timedForecastViewMapper: TimedForecastViewMapper): Mapper<RawWeatherView,RawWeather> {
    override fun mapToView(type: RawWeather): RawWeatherView {
        return RawWeatherView(
            timedForecasts = type.timedForecasts.map { timedForecast ->
                timedForecastViewMapper.mapToView(timedForecast)
            }
        )
    }
}