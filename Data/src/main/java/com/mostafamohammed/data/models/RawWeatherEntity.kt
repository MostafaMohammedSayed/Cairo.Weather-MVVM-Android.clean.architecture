package com.mostafamohammed.data.models

import com.mostafamohammed.data.Entity

data class RawWeatherEntity(
    val timedForecasts: List<TimedForecastEntity>
): Entity
