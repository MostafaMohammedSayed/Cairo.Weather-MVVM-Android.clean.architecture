package com.mostafamohammed.data.mapper

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.TimedForecast
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TimedForecastMapperTest {
    private lateinit var mapper: TimedForecastMapper
    private lateinit var entityModel: TimedForecastEntity
    private lateinit var domainModel: TimedForecast

    @Before
    fun setUp() {
        mapper = TimedForecastMapper(ForecastAttributesMapper())

        entityModel =
            TimedForecastEntity(
                attrs = ForecastAttributesEntity(
                    temp = 25.0,
                    tempMax = 27.0,
                    tempMin = 23.0,
                    pressure = 1000.1
                ),
                date = "25-12-1956"
            )

        domainModel =
            TimedForecast(
                attrs = ForecastAttributes(
                    temp = 25.0,
                    tempMax = 27.0,
                    tempMin = 23.0,
                    pressure = 1000.1
                ),
                date = "25-12-1956"
            )

    }

    @Test
    fun mapFromEntityMapsData() {
        val resultModel = mapper.mapFromEntity(entityModel)
        assertEqualData(entityModel, resultModel)
    }

    @Test
    fun mapToEntityMapsData() {
        val resultModel = mapper.mapToEntity(domainModel)
        assertEqualData(resultModel, domainModel)
    }

    private fun assertEqualData(entity: TimedForecastEntity, model: TimedForecast) {
        Assert.assertEquals(entity.date, model.date)
        Assert.assertEquals(
            25.0,
            entity.attrs.temp,
            model.attrs.temp
        )
        Assert.assertEquals(
            23.0,
            entity.attrs.tempMin,
            model.attrs.tempMin
        )
        Assert.assertEquals(
            27.0,
            entity.attrs.tempMax,
            model.attrs.tempMax
        )
        Assert.assertEquals(
            1000.1,
            entity.attrs.pressure,
            model.attrs.pressure
        )
    }
}