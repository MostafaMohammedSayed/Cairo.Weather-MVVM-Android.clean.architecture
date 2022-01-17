package com.mostafamohammed.presentation.mapper

import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.presentation.model.ForecastAttributesView

class ForecastAttributesViewMapper: Mapper<ForecastAttributesView, ForecastAttributes> {

    override fun mapToView (model: ForecastAttributes): ForecastAttributesView {
        return ForecastAttributesView(
            temp = model.temp,
            tempMin = model.tempMin,
            tempMax = model.tempMax,
            pressure = model.pressure
        )
    }

}