package com.mostafamohammed.remote.mappers

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.remote.models.ForecastAttributesModel
import com.mostafamohammed.remote.models.TimedForecastModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TimedForecastModelMapperTest {
    private lateinit var mapper: TimedForecastModelMapper
    private lateinit var entityModel: TimedForecastEntity
    private lateinit var remoteModel: TimedForecastModel

    @Before
    fun setUp() {
        mapper = TimedForecastModelMapper(ForecastAttributesModelMapper())

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

        remoteModel =
            TimedForecastModel(
                attrs = ForecastAttributesModel(
                    temp = 25.0,
                    tempMax = 27.0,
                    tempMin = 23.0,
                    pressure = 1000.1
                ),
                date = "25-12-1956"
            )

    }

    @Test
    fun mapFromModelMapsData() {
        val resultModel = mapper.mapFromModel(remoteModel)
        assertEqualData(resultModel, remoteModel)
    }


    private fun assertEqualData(entity: TimedForecastEntity, model: TimedForecastModel) {
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