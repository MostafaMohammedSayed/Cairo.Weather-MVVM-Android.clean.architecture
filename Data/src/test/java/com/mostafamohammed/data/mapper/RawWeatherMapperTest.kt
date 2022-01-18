package com.mostafamohammed.data.mapper

import com.mostafamohammed.data.ModelFactory
import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.models.TimedForecast
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class RawWeatherMapperTest {
    private lateinit var mapper: RawWeatherMapper
    private lateinit var timedForecastMapper: TimedForecastMapper

    @Before
    fun setUp() {
        timedForecastMapper = mock()
        mapper = RawWeatherMapper(timedForecastMapper)
    }

    private fun stubTimedForecastMapperMapToEntity(model: TimedForecast) {
        whenever(timedForecastMapper.mapToEntity(model))
            .thenReturn(
                TimedForecastEntity(
                    attrs = toEntityAttr(model.attrs),
                    date = model.date
                )
            )
    }

    private fun toEntityAttr(attrs: ForecastAttributes): ForecastAttributesEntity {
        return ForecastAttributesEntity(
            temp = attrs.temp,
            tempMin = attrs.tempMin,
            tempMax = attrs.tempMax,
            pressure = attrs.pressure
        )
    }

    private fun stubTimedForecastMapperMapFromEntity(entity: TimedForecastEntity) {
        whenever(timedForecastMapper.mapFromEntity(entity))
            .thenReturn(
                TimedForecast(
                    attrs = fromEntityAttr(entity.attrs),
                    date = entity.date
                )
            )
    }

    private fun fromEntityAttr(attrs: ForecastAttributesEntity): ForecastAttributes {
        return ForecastAttributes(
            temp = attrs.temp,
            tempMin = attrs.tempMin,
            tempMax = attrs.tempMax,
            pressure = attrs.pressure
        )
    }

    @Test
    fun mapToEntityMapsData() {
        val model = ModelFactory.makeRawWeather()
        model.timedForecasts.forEach { timedForecast ->
            stubTimedForecastMapperMapToEntity(timedForecast)
        }
        val entity = mapper.mapToEntity(model)
        assertEqualData(entity, model)
    }

    @Test
    fun mapFromEntityMapsData() {
        val entity = ModelFactory.makeRawWeatherEntity()
        entity.timedForecasts.forEach { timedForecastEntity ->
            stubTimedForecastMapperMapFromEntity(timedForecastEntity)
        }
        val model = mapper.mapFromEntity(entity)
        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: RawWeatherEntity, model: RawWeather) {
        val entities = entity.timedForecasts
        val models = model.timedForecasts
        for (i in entities.indices) {
            assert(entities[i].date == models[i].date)
            assert(entities[i].attrs.temp == models[i].attrs.temp)
            assert(entities[i].attrs.tempMin == models[i].attrs.tempMin)
            assert(entities[i].attrs.tempMax == models[i].attrs.tempMax)
            assert(entities[i].attrs.pressure == models[i].attrs.pressure)
        }
    }
}