package com.mostafamohammed.data.mapper

import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.domain.models.TimedForecast

class TimedForecastMapper(private val forecastAttrsMapper: ForecastAttributesMapper) :
    EntityMapper<TimedForecastEntity, TimedForecast> {

    override fun mapFromEntity(entity: TimedForecastEntity): TimedForecast {
        return TimedForecast(
            attrs = forecastAttrsMapper.mapFromEntity(entity.attrs),
            date = entity.date
        )
    }

    override fun mapToEntity(domain: TimedForecast): TimedForecastEntity {
        return TimedForecastEntity(
            attrs = forecastAttrsMapper.mapToEntity(domain.attrs),
            date = domain.date
        )
    }
}