package com.mostafamohammed.remote.mappers

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.remote.models.ForecastAttributesModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ForecastAttributesModelMapperTest {
    private lateinit var mapper: ForecastAttributesModelMapper
    private lateinit var entityModel: ForecastAttributesEntity
    private lateinit var remoteModel: ForecastAttributesModel

    @Before
    fun setUp() {
        mapper = ForecastAttributesModelMapper()

        entityModel = ForecastAttributesEntity(
            temp = 25.0,
            tempMax = 27.0,
            tempMin = 23.0,
            pressure = 1000.1
        )

        remoteModel = ForecastAttributesModel(
            temp = 25.0,
            tempMax = 27.0,
            tempMin = 23.0,
            pressure = 1000.1
        )
    }

    @Test
    fun mapFromModelMapsData() {
        val resultModel = mapper.mapFromModel(remoteModel)
        assertEqualData(resultModel, remoteModel)
    }


    private fun assertEqualData(entity: ForecastAttributesEntity, model: ForecastAttributesModel) {
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