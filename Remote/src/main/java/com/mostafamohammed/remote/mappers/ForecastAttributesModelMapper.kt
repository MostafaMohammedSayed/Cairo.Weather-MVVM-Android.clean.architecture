package com.mostafamohammed.remote.mappers

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.remote.models.ForecastAttributesModel

class ForecastAttributesModelMapper: ModelMapper<ForecastAttributesModel,ForecastAttributesEntity> {

    override fun mapFromModel(model: ForecastAttributesModel): ForecastAttributesEntity {
        return ForecastAttributesEntity(
            temp = model.temp,
            tempMin = model.tempMin,
            tempMax = model.tempMax,
            pressure = model.pressure
        )
    }

}