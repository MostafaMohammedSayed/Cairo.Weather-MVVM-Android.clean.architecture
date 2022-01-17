package com.mostafamohammed.data.repository

import com.mostafamohammed.data.models.RawWeatherEntity
import io.reactivex.rxjava3.core.Observable

interface RawWeatherDataSource {
    fun getForecast(units: String,apiKey: String): Observable<RawWeatherEntity>
}