package com.mostafamohammed.remote

import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.repository.RawWeatherRemote
import com.mostafamohammed.remote.apiService.ApiService
import com.mostafamohammed.remote.mappers.RawWeatherModelMapper
import io.reactivex.rxjava3.core.Observable

class RawWeatherRemoteImpl(
    private val service: ApiService,
    private val mapper: RawWeatherModelMapper
) : RawWeatherRemote {
    override fun getForecast(units: String, apiKey: String): Observable<RawWeatherEntity> {
        return service.getWeather(units, apiKey)
            .map { rawWeatherModel ->
                mapper.mapFromModel(rawWeatherModel)
            }
    }
}