package com.mostafamohammed.data.sources

import com.mostafamohammed.data.mapper.RawWeatherMapper
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Observable

/*
*this class is intended for implementing domain layer interface WeatherForecastRepository
*if the app has a cache layer another constructor parameter will be added and i will be
*a factory class that provides cache data source or remote data source
*/
class RawWeatherDataSource(
    private val mapper: RawWeatherMapper,
    private val rawWeatherRemoteDataSource: RawWeatherRemoteDataSource
) :
    WeatherForecastRepository {

    override fun getForecast(units: String, apikey: String): Observable<RawWeather> {
        return rawWeatherRemoteDataSource.getForecast(units, apikey)
            .map { rawWeatherEntity ->
                mapper.mapFromEntity(rawWeatherEntity)
            }
    }
}