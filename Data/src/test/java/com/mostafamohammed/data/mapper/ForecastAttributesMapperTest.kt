package com.mostafamohammed.data.mapper

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.domain.models.ForecastAttributes
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ForecastAttributesMapperTest {
    private lateinit var mapper: ForecastAttributesMapper
    private lateinit var entityModel: ForecastAttributesEntity
    private lateinit var domainModel: ForecastAttributes

    @Before
    fun setUp() {
        mapper = ForecastAttributesMapper()

        entityModel = ForecastAttributesEntity(
            temp = 25.0,
            tempMax = 27.0,
            tempMin = 23.0,
            pressure = 1000.1
        )

        domainModel = ForecastAttributes(
            temp = 25.0,
            tempMax = 27.0,
            tempMin = 23.0,
            pressure = 1000.1
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

    private fun assertEqualData(entity: ForecastAttributesEntity, model: ForecastAttributes) {
        Assert.assertEquals(
            25.0,
            entity.temp,
            model.temp
        )
        Assert.assertEquals(
            23.0,
            entity.tempMin,
            model.tempMin
        )
        Assert.assertEquals(
            27.0,
            entity.tempMax,
            model.tempMax
        )
        Assert.assertEquals(
            1000.1,
            entity.pressure,
            model.pressure
        )
    }
}