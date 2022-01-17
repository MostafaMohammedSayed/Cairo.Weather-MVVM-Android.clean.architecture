package com.mostafamohammed.remote.mappers

import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.remote.models.TimedForecastModel

class TimedForecastModelMapper(private val forecastAttrsModelMapper: ForecastAttributesModelMapper) :
    ModelMapper<TimedForecastModel, TimedForecastEntity> {

    override fun mapFromModel(model: TimedForecastModel): TimedForecastEntity {
        return TimedForecastEntity(
            attrs = forecastAttrsModelMapper.mapFromModel(model.attrs),
            date = model.date
        )
    }

}