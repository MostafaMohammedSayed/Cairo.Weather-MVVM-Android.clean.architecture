package com.mostafamohammed.presentation.mapper

import com.mostafamohammed.domain.models.TimedForecast
import com.mostafamohammed.presentation.model.TimedForecastView

class TimedForecastViewMapper(private val forecastAttrsViewMapper: ForecastAttributesViewMapper) :
    Mapper<TimedForecastView, TimedForecast> {

    override fun mapToView (model: TimedForecast): TimedForecastView {
        return TimedForecastView(
            attrs = forecastAttrsViewMapper.mapToView(model.attrs),
            date = model.date
        )
    }

}