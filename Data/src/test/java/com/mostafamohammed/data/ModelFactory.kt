package com.mostafamohammed.data

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.models.TimedForecast

object ModelFactory {
    fun makeRawWeather(): RawWeather{
        return RawWeather(
            timedForecasts = makeTimedForecasts()
        )
    }

   fun makeTimedForecasts(): List<TimedForecast> {
        val timedForecasts = mutableListOf<TimedForecast>()
        repeat(2){
            timedForecasts.add(
                TimedForecast(
                attrs = makeAttrs(),
                    date = DataFactory.randomString()
            )
            )
        }
        return timedForecasts
    }

    fun makeAttrs(): ForecastAttributes {
        return ForecastAttributes(
            temp = DataFactory.randomDouble(),
            tempMin = DataFactory.randomDouble(),
            tempMax = DataFactory.randomDouble(),
            pressure = DataFactory.randomDouble()
        )
    }

    fun makeRawWeatherEntity(): RawWeatherEntity {
        return RawWeatherEntity(
            timedForecasts = makeTimedForecastsEntity()
        )
    }

    fun makeTimedForecastsEntity(): List<TimedForecastEntity> {
        val timedForecasts = mutableListOf<TimedForecastEntity>()
        repeat(2){
            timedForecasts.add(
                TimedForecastEntity(
                    attrs = makeAttrsEntity(),
                    date = DataFactory.randomString()
                )
            )
        }
        return timedForecasts
    }

    fun makeAttrsEntity(): ForecastAttributesEntity {
        return ForecastAttributesEntity(
            temp = DataFactory.randomDouble(),
            tempMin = DataFactory.randomDouble(),
            tempMax = DataFactory.randomDouble(),
            pressure = DataFactory.randomDouble()
        )
    }
}