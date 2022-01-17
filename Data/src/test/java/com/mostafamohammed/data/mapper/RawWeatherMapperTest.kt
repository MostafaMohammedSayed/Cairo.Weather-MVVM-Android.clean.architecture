package com.mostafamohammed.data.mapper

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.models.TimedForecast
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RawWeatherMapperTest {
    private lateinit var mapper: RawWeatherMapper
    private lateinit var entityModel: RawWeatherEntity
    private lateinit var domainModel: RawWeather

    @Before
    fun setUp() {

        mapper = RawWeatherMapper(TimedForecastMapper(ForecastAttributesMapper()))

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

        domainModel = RawWeather(
            timedForecasts = listOf(
                TimedForecast(
                    attrs = ForecastAttributes(
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
    fun mapFromEntityMapsData() {
        val resultModel = mapper.mapFromEntity(entityModel)
        assertEqualData(entityModel, resultModel)
    }

    @Test
    fun mapToEntityMapsData() {
        val resultModel = mapper.mapToEntity(domainModel)
        assertEqualData(resultModel, domainModel)
    }

    private fun assertEqualData(entity: RawWeatherEntity, model: RawWeather) {
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