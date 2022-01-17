package com.mostafamohammed.remote.models

import com.google.gson.annotations.SerializedName
import com.mostafamohammed.remote.RemoteModel


data class RawWeatherModel(
    @SerializedName("list")
    val timedForecasts: List<TimedForecastModel>
): RemoteModel
