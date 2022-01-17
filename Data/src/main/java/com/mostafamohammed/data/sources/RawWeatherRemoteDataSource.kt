package com.mostafamohammed.data.sources

import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.repository.RawWeatherDataSource
import com.mostafamohammed.data.repository.RawWeatherRemote
import io.reactivex.rxjava3.core.Observable

class RawWeatherRemoteDataSource(private val rawWeatherRemote:RawWeatherRemote): RawWeatherDataSource {
    override fun getForecast(units: String, apiKey: String): Observable<RawWeatherEntity> {
        return rawWeatherRemote.getForecast(units,apiKey)
    }
}