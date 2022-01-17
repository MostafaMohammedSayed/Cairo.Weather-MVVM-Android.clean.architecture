package com.mostafamohammed.remote.mappers

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.remote.models.ForecastAttributesModel
import com.mostafamohammed.remote.models.RawWeatherModel
import com.mostafamohammed.remote.models.TimedForecastModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RawWeatherModelMapperTest {
    private lateinit var mapper: RawWeatherModelMapper
    private lateinit var entityModel: RawWeatherEntity
    private lateinit var remoteModel: RawWeatherModel

    @Before
    fun setUp() {

        mapper = RawWeatherModelMapper(TimedForecastModelMapper(ForecastAttributesModelMapper()))

        entityModel = RawWeatherEntity(
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
    }

    @Test
    fun mapFromModelMapsData() {
        val resultModel = mapper.mapFromModel(remoteModel)
        assertEqualData(resultModel, remoteModel)
    }

    private fun assertEqualData(entity: RawWeatherEntity, model: RawWeatherModel) {
        Assert.assertEquals(entity.timedForecasts[0].date, model.timedForecasts[0].date)
        Assert.assertEquals(
            25.0,
            entity.timedForecasts[0].attrs.temp,
            model.timedForecasts[0].attrs.temp
        )
        Assert.assertEquals(
            23.0,
            entity.timedForecasts[0].attrs.tempMin,
            model.timedForecasts[0].attrs.tempMin
        )
        Assert.assertEquals(
            27.0,
            entity.timedForecasts[0].attrs.tempMax,
            model.timedForecasts[0].attrs.tempMax
        )
        Assert.assertEquals(
            1000.1,
            entity.timedForecasts[0].attrs.pressure,
            model.timedForecasts[0].attrs.pressure
        )
    }
}