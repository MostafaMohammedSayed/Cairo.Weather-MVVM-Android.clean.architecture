package com.mostafamohammed.data.mapper

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.domain.models.ForecastAttributes

class ForecastAttributesMapper: EntityMapper<ForecastAttributesEntity,ForecastAttributes> {
    override fun mapFromEntity(entity: ForecastAttributesEntity): ForecastAttributes {
        return ForecastAttributes(
            temp = entity.temp,
            tempMin = entity.tempMin,
            tempMax = entity.tempMax,
            pressure = entity.pressure
        )
    }

    override fun mapToEntity(domain: ForecastAttributes): ForecastAttributesEntity {
        return ForecastAttributesEntity(
            temp = domain.temp,
            tempMin = domain.tempMin,
            tempMax = domain.tempMax,
            pressure = domain.pressure
        )    }
}