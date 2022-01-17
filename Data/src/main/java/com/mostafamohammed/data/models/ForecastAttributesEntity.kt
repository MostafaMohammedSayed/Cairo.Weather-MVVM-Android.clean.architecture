package com.mostafamohammed.data.models

import com.mostafamohammed.data.Entity

data class ForecastAttributesEntity(
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Double
): Entity
