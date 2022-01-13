package com.mostafamohammed.domain.models

import com.mostafamohammed.domain.DomainModel

data class RawWeather(
    val timedForecasts: List<TimedForecast>
): DomainModel
