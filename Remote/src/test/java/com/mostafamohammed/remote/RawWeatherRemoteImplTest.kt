package com.mostafamohammed.remote

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.remote.apiService.ApiService
import com.mostafamohammed.remote.mappers.ForecastAttributesModelMapper
import com.mostafamohammed.remote.mappers.RawWeatherModelMapper
import com.mostafamohammed.remote.mappers.TimedForecastModelMapper
import com.mostafamohammed.remote.models.ForecastAttributesModel
import com.mostafamohammed.remote.models.RawWeatherModel
import com.mostafamohammed.remote.models.TimedForecastModel
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RawWeatherRemoteImplTest {

    private lateinit var rawWeatherRemoteImpl: RawWeatherRemoteImpl
    private lateinit var mapper: RawWeatherModelMapper
    private lateinit var apiService: ApiService
    private lateinit var remoteModel: RawWeatherModel
    private lateinit var entityModel: RawWeatherEntity
    private lateinit var weatherService: WeatherService

    val service = mock(WeatherService::class.java)


    @Before
    fun setUp() {
        remoteModel = RawWeatherModel(
            timedForecasts = listOf(
                TimedForecastModel(
                    attrs = ForecastAttributesModel(
                        temp = 25.0,
                        tempMax = 27.0,
                        tempMin = 23.0,
                        pressure = 1000.1
                    ),
                    date = "25-12-1956"
                )
            )
        )

        apiService = object : ApiService {
            override fun getWeather(units: String, appid: String): Observable<RawWeatherModel> {
                return Observable.just(remoteModel)
            }

        }
        mapper = RawWeatherModelMapper(TimedForecastModelMapper(ForecastAttributesModelMapper()))
        rawWeatherRemoteImpl = RawWeatherRemoteImpl(apiService, mapper)

        entityModel = RawWeatherEntity(
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

        weatherService = WeatherService()
    }

    @Test
    fun getForecastCompletes() {
        val testObserver = rawWeatherRemoteImpl.getForecast("units", "apiKey").test()
        testObserver.assertComplete()
    }

    @Test
    fun getForecastReturnsData() {
        val testObserver = rawWeatherRemoteImpl.getForecast("units", "apiKey").test()
        testObserver.assertValue(entityModel)
    }

    @Test
    fun getForecastCallsServer() {
        rawWeatherRemoteImpl.getForecast("units", "apiKey").test()
        verify(service).weatherApi.getWeather("units", "apiKey")
    }

}