package com.mostafamohammed.remote.models

import com.mostafamohammed.remote.RemoteModel


data class ForecastAttributesModel(
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Double
): RemoteModel
