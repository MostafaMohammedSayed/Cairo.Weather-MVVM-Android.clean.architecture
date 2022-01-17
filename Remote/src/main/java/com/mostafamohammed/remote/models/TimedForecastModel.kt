package com.mostafamohammed.remote.models

import com.google.gson.annotations.SerializedName
import com.mostafamohammed.remote.RemoteModel


data class TimedForecastModel(
    @SerializedName("main")
    val attrs: ForecastAttributesModel,
    @SerializedName("dt_txt")
    val date: String
): RemoteModel
