package com.mostafamohammed.domain.models

import com.mostafamohammed.domain.DomainModel


data class ForecastAttributes(
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Double
): DomainModel
