package com.mostafamohammed.remote.models

import com.google.gson.annotations.SerializedName
import com.mostafamohammed.remote.RemoteModel


data class ForecastAttributesModel(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("pressure")
    val pressure: Double
): RemoteModel
