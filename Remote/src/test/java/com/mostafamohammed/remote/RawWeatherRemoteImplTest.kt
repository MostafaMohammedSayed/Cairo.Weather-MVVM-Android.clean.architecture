package com.mostafamohammed.remote

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.remote.apiService.ApiService
import com.mostafamohammed.remote.mappers.RawWeatherModelMapper
import com.mostafamohammed.remote.models.RawWeatherModel
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class RawWeatherRemoteImplTest {

    private lateinit var rawWeatherRemoteImpl: RawWeatherRemoteImpl
    @Mock
    private lateinit var service: ApiService
    @Mock
    private lateinit var mapper: RawWeatherModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        rawWeatherRemoteImpl = RawWeatherRemoteImpl(service, mapper)
    }

    fun stubServiceGetWeather(units: String, apiKey: String, model: RawWeatherModel) {
        whenever(service.getWeather(units, apiKey))
            .thenReturn(Observable.just(model))
    }

    fun stubMapperMapFromModel(model: RawWeatherModel) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(RawWeatherEntity(
                timedForecasts = model.timedForecasts.map { timedForecastModel ->
                    TimedForecastEntity(
                        attrs = ForecastAttributesEntity(
                            temp = timedForecastModel.attrs.temp,
                            tempMin = timedForecastModel.attrs.tempMin,
                            tempMax = timedForecastModel.attrs.tempMax,
                            pressure = timedForecastModel.attrs.pressure,
                        ),
                        date = timedForecastModel.date
                    )
                }
            ))
    }

    @Test
    fun getForecastCompletes() {
        val model = RemoteLayerModelFactory.makeRawWeatherModel()
        stubServiceGetWeather("metric", "apiKey", model)
        stubMapperMapFromModel(model)
        val testObserver = rawWeatherRemoteImpl.getForecast("metric", "apiKey").test()
        testObserver.assertComplete()
    }

    @Test
    fun getForecastReturnsData() {
        val model = RemoteLayerModelFactory.makeRawWeatherModel()
        val entity = RawWeatherEntity(
            timedForecasts = model.timedForecasts.map { timedForecastModel ->
                TimedForecastEntity(
                    attrs = ForecastAttributesEntity(
                        temp = timedForecastModel.attrs.temp,
                        tempMin = timedForecastModel.attrs.tempMin,
                        tempMax = timedForecastModel.attrs.tempMax,
                        pressure = timedForecastModel.attrs.pressure,
                    ),
                    date = timedForecastModel.date
                )
            })
        stubServiceGetWeather("metric", "apiKey", model)
        stubMapperMapFromModel(model)
        val testObserver = rawWeatherRemoteImpl.getForecast("metric", "apiKey").test()
        testObserver.assertValue(entity)
    }

    @Test
    fun getForecastCallsServer() {
        val model = RemoteLayerModelFactory.makeRawWeatherModel()
        stubServiceGetWeather("metric", "apiKey", model)
        stubMapperMapFromModel(model)
        rawWeatherRemoteImpl.getForecast("metric", "apiKey").test()
        verify(service).getWeather("metric", "apiKey")
    }
}