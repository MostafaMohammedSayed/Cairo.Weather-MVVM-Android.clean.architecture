package com.mostafamohammed.data.mapper

import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.domain.models.RawWeather

class RawWeatherMapper(private val timedForecastMapper: TimedForecastMapper) :
    EntityMapper<RawWeatherEntity, RawWeather> {

    override fun mapFromEntity(entity: RawWeatherEntity): RawWeather {
        return RawWeather(
            timedForecasts = entity.timedForecasts.map { timedForecastEntity ->
                timedForecastMapper.mapFromEntity(timedForecastEntity)
            }
        )
    }

    override fun mapToEntity(domain: RawWeather): RawWeatherEntity {
        return RawWeatherEntity(
            timedForecasts = domain.timedForecasts.map { timedForecast ->
                timedForecastMapper.mapToEntity(timedForecast)
            }
        )
    }
}