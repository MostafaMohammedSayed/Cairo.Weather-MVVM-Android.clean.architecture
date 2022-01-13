package com.mostafamohammed.domain.useCases

import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.models.TimedForecast
import com.mostafamohammed.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Observable

class FakeWeatherForecastRepository(val rawWeather: RawWeather): WeatherForecastRepository {

    override fun getForecast(units: String, apikey: String): Observable<RawWeather> {
        return Observable.just(rawWeather)
    }

}
