package com.mostafamohammed.remote.mappers

import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.remote.models.RawWeatherModel

class RawWeatherModelMapper(private val timedForecastModelMapper: TimedForecastModelMapper) :
    ModelMapper<RawWeatherModel,RawWeatherEntity> {

    override fun mapFromModel(model: RawWeatherModel): RawWeatherEntity {
        return RawWeatherEntity(
            timedForecasts = model.timedForecasts.map { timedForecastModel ->
                timedForecastModelMapper.mapFromModel(timedForecastModel)
            }
        )
    }

}