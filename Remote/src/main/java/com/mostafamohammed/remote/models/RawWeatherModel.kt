package com.mostafamohammed.remote.models

import com.mostafamohammed.remote.RemoteModel


data class RawWeatherModel(
    val timedForecasts: List<TimedForecastModel>
): RemoteModel
