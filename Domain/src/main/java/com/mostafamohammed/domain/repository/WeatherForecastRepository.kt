package com.mostafamohammed.domain.repository

import com.mostafamohammed.domain.models.RawWeather
import io.reactivex.rxjava3.core.Observable

interface WeatherForecastRepository {
    fun getForecast(units: String,apikey: String): Observable<RawWeather>
}