package com.mostafamohammed.domain.models

import com.mostafamohammed.domain.DomainModel


data class TimedForecast(
    val attrs: ForecastAttributes,
    val date: String
): DomainModel
