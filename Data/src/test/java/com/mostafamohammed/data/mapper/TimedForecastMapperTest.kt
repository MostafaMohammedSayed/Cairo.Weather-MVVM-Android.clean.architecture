package com.mostafamohammed.data.mapper

import com.mostafamohammed.data.ModelFactory
import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.TimedForecast
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class TimedForecastMapperTest {

    @Mock
    private lateinit var forecastAttrsMapper: ForecastAttributesMapper

    lateinit var mapper: TimedForecastMapper

    @Before
    fun setUp() {
        forecastAttrsMapper = mock()
        mapper = TimedForecastMapper(forecastAttrsMapper)
    }

    private fun stubForecastAttributesMapperMapToEntity(model: ForecastAttributes) {
        whenever(forecastAttrsMapper.mapToEntity(model))
            .thenReturn(
                ForecastAttributesEntity(
                    temp = model.temp,
                    tempMin = model.tempMin,
                    tempMax = model.tempMax,
                    pressure = model.pressure
                )
            )
    }

    private fun stubForecastAttributesMapperMapFromEntity(entity: ForecastAttributesEntity) {
        whenever(forecastAttrsMapper.mapFromEntity(entity))
            .thenReturn(
                ForecastAttributes(
                    temp = entity.temp,
                    tempMin = entity.tempMin,
                    tempMax = entity.tempMax,
                    pressure = entity.pressure
                )
            )
    }

    @Test
    fun mapFromEntityMapsData() {
        val entities = ModelFactory.makeTimedForecastsEntity()
        entities.forEach { entity ->
            stubForecastAttributesMapperMapFromEntity(entity.attrs)
        }
        val models = entities.map { entitiesToMap ->
            mapper.mapFromEntity(entitiesToMap)
        }
        assertEqualData(entities, models)
    }

    @Test
    fun mapToEntityMapsData() {
        val models = ModelFactory.makeTimedForecasts()
        models.forEach { model ->
            stubForecastAttributesMapperMapToEntity(model.attrs)
        }
        val entities = models.map { modelsToMap ->
            mapper.mapToEntity(modelsToMap)
        }
        assertEqualData(entities, models)
    }

    private fun assertEqualData(entities: List<TimedForecastEntity>, models: List<TimedForecast>) {
        for (i in entities.indices) {
            assert(entities[i].date == models[i].date)
            assert(entities[i].attrs.temp == models[i].attrs.temp)
            assert(entities[i].attrs.tempMin == models[i].attrs.tempMin)
            assert(entities[i].attrs.tempMax == models[i].attrs.tempMax)
            assert(entities[i].attrs.pressure == models[i].attrs.pressure)
        }
    }
}