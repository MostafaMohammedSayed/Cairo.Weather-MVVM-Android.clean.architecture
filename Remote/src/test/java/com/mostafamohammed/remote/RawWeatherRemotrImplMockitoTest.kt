package com.mostafamohammed.remote

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.remote.mappers.RawWeatherModelMapper
import com.mostafamohammed.remote.models.ForecastAttributesModel
import com.mostafamohammed.remote.models.RawWeatherModel
import com.mostafamohammed.remote.models.TimedForecastModel
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class RawWeatherRemotrImplMockitoTest {
    private val mapper = mock(RawWeatherModelMapper::class.java)
    private val service = mock(WeatherService::class.java)
    private val rawWeatherRemoteImpl = mock(RawWeatherRemoteImpl::class.java)

    private lateinit var remoteModel: RawWeatherModel
    private lateinit var entity: RawWeatherEntity

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
    }

    @Test
    fun getWeatherCompletes(){
        stubWeatherServiceGetWeather(Observable.just(remoteModel))
        stubRawWeatherModelMapperMapFromModel(any(),entity)

        val testObserver = rawWeatherRemoteImpl.getForecast("metric","660a1bcf9fb3c5e90d06039601866186").test()
        testObserver.assertComplete()
    }

    private fun stubWeatherServiceGetWeather(observable: Observable<RawWeatherModel>){
        Mockito.`when`(service.weatherApi.getWeather("metric","660a1bcf9fb3c5e90d06039601866186"))
            .thenReturn(observable)
    }

    private fun stubRawWeatherModelMapperMapFromModel(model: RawWeatherModel, entity: RawWeatherEntity){
        Mockito.`when`(mapper.mapFromModel(model))
            .thenReturn(entity)
    }
}