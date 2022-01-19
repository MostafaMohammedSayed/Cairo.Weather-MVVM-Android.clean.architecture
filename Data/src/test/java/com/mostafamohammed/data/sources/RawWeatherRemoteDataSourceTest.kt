package com.mostafamohammed.data.sources

import com.mostafamohammed.data.ModelFactory
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.repository.RawWeatherRemote
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class RawWeatherRemoteDataSourceTest {
    private lateinit var rawWeatherRemoteDataSource: RawWeatherRemoteDataSource
    @Mock
    private lateinit var rawWeatherRemote: RawWeatherRemote

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        rawWeatherRemoteDataSource = RawWeatherRemoteDataSource(rawWeatherRemote)
    }

    private fun stubRawWeatherRemoteGetForecast(
        entity: RawWeatherEntity
    ) {
        whenever(rawWeatherRemote.getForecast("metric", "apiKey"))
            .thenReturn(Observable.just(entity))
    }

    @Test
    fun getForecastCompletes() {
        val entity = ModelFactory.makeRawWeatherEntity()
        stubRawWeatherRemoteGetForecast(entity)
        val testObserver = rawWeatherRemoteDataSource.getForecast("metric", "apiKey").test()
        testObserver.assertComplete()
    }

    @Test
    fun getForecastReturnsData() {
        val entity = ModelFactory.makeRawWeatherEntity()
        stubRawWeatherRemoteGetForecast(entity)
        val testObserver = rawWeatherRemoteDataSource.getForecast("metric", "apiKey").test()
        testObserver.assertValue(entity)
    }
}