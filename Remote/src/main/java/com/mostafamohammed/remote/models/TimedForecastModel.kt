package com.mostafamohammed.remote.models

import com.mostafamohammed.remote.RemoteModel


data class TimedForecastModel(
    val attrs: ForecastAttributesModel,
    val date: String
): RemoteModel
