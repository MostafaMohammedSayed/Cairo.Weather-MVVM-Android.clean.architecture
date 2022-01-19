package com.mostafamohammed.data.sources

import com.mostafamohammed.data.ModelFactory
import com.mostafamohammed.data.mapper.RawWeatherMapper
import com.mostafamohammed.data.models.RawWeatherEntity
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class RawWeatherRepositoryTest {
    private lateinit var rawWeatherRepository: RawWeatherRepository

    @Mock
    private lateinit var rawWeatherRemoteDataSource: RawWeatherRemoteDataSource
    @Mock
    private lateinit var mapper: RawWeatherMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        rawWeatherRepository = RawWeatherRepository(mapper, rawWeatherRemoteDataSource)
    }

    private fun stubRawWeatherRemoteDataSourceGetForecast(
        units: String = "metric",
        apikey: String = "apiKey",
        entity: RawWeatherEntity
    ) {
        whenever(rawWeatherRemoteDataSource.getForecast(units, apikey))
            .thenReturn(Observable.just(entity))
    }

    private fun stubRawWeatherMapperMapFromEntity(entity: RawWeatherEntity) {
        whenever(mapper.mapFromEntity(entity))
            .thenReturn(ModelFactory.fromEntityToDomain(entity))
    }

    @Test
    fun getForecastCompletes() {
        val entity = ModelFactory.makeRawWeatherEntity()
        stubRawWeatherRemoteDataSourceGetForecast(entity = entity)
        stubRawWeatherMapperMapFromEntity(entity)
        val testObserver = rawWeatherRepository.getForecast("metric", "apiKey").test()
        testObserver.assertComplete()
    }

    @Test
    fun getForecastReturnsData(){
        val entity = ModelFactory.makeRawWeatherEntity()
        val domain = ModelFactory.fromEntityToDomain(entity)
        stubRawWeatherRemoteDataSourceGetForecast(entity = entity)
        stubRawWeatherMapperMapFromEntity(entity)
        val testObserver = rawWeatherRepository.getForecast("metric", "apiKey").test()
        testObserver.assertValue(domain)
    }

}