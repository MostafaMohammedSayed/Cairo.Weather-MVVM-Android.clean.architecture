package com.mostafamohammed.domain.usecases.checkForecast

import com.mostafamohammed.domain.ObservableUseCase
import com.mostafamohammed.domain.executer.PostExecutionThread
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Observable

class GetForecast(
    private val forecastRepository: WeatherForecastRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<RawWeather, GetForecast.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<RawWeather> {
        if (params == null) {
            throw IllegalArgumentException("Params can't be null")
        }
        return forecastRepository.getForecast(params.units, params.apiKey)
    }

    data class Params(val units: String, val apiKey: String) {
        companion object {
            fun createParams(units: String, apiKey: String): Params {
                return Params(
                    units = units,
                    apiKey = apiKey
                )
            }
        }
    }
}