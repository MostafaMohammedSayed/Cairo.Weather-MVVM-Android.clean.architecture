package com.mostafamohammed.data.sources

import com.mostafamohammed.data.mapper.ForecastAttributesMapper
import com.mostafamohammed.data.mapper.RawWeatherMapper
import com.mostafamohammed.data.mapper.TimedForecastMapper
import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.data.repository.RawWeatherRemote
import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.models.TimedForecast
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test

class RawWeatherDataSourceTest {
    private lateinit var entity: RawWeatherEntity
    private lateinit var rawWeatherDataSource: RawWeatherDataSource
    private lateinit var rawWeatherRemoteDataSource: RawWeatherRemoteDataSource
    private lateinit var rawWeatherRemote: RawWeatherRemote
    private lateinit var mapper: RawWeatherMapper
    private lateinit var domain: RawWeather

    @Before
    fun setUp() {
        entity = RawWeatherEntity(
            timedForecasts = listOf(
                TimedForecastEntity(
                    attrs = ForecastAttributesEntity(
                        temp = 25.0,
                        tempMax = 27.0,
                        tempMin = 23.0,
                        pressure = 1000.1
                    ),
                    date = "25-12-1956"
                )
            )
        )
        rawWeatherRemote = object : RawWeatherRemote {
            override fun getForecast(units: String, apiKey: String): Observable<RawWeatherEntity> {

                return Observable.just(entity)
            }

        }
        rawWeatherRemoteDataSource = RawWeatherRemoteDataSource(rawWeatherRemote)
        mapper = RawWeatherMapper(TimedForecastMapper(ForecastAttributesMapper()))
        rawWeatherDataSource = RawWeatherDataSource(mapper, rawWeatherRemoteDataSource)

        domain = RawWeather(
            timedForecasts = listOf(
                TimedForecast(
                    attrs = ForecastAttributes(
                        temp = 25.0,
                        tempMax = 27.0,
                        tempMin = 23.0,
                        pressure = 1000.1
                    ),
                    date = "25-12-1956"
                )
            )
        )
    }

    @Test
    fun getForecastCompletes() {
        val testObservable = rawWeatherDataSource.getForecast("units", "apiKey").test()
        testObservable.assertComplete()
    }

    @Test
    fun getForecastReturnsData() {
        val testObservable = rawWeatherDataSource.getForecast("units", "apiKey").test()
        testObservable.assertValue(domain)
    }
}