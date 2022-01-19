package com.mostafamohammed.remote.mappers

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.remote.RemoteLayerModelFactory
import com.mostafamohammed.remote.models.RawWeatherModel
import com.mostafamohammed.remote.models.TimedForecastModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class RawWeatherModelMapperTest {
    private lateinit var mapper: RawWeatherModelMapper

    @Mock
    private lateinit var timedForecastModelMapper: TimedForecastModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mapper = RawWeatherModelMapper(timedForecastModelMapper)
    }

    private fun stubTimedForecastModelMapperMapFromModel(model: TimedForecastModel) {
        whenever(timedForecastModelMapper.mapFromModel(model))
            .thenReturn(
                TimedForecastEntity(
                    attrs = ForecastAttributesEntity(
                        temp = model.attrs.temp,
                        tempMin = model.attrs.tempMin,
                        tempMax = model.attrs.tempMax,
                        pressure = model.attrs.pressure,
                    ),
                    date = model.date
                )
            )
    }

    @Test
    fun mapFromModelMapsData() {
        val model = RemoteLayerModelFactory.makeRawWeatherModel()
        model.timedForecasts.map { timedForecastModel ->
            stubTimedForecastModelMapperMapFromModel(timedForecastModel)
        }
        val entity = mapper.mapFromModel(model)
        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: RawWeatherEntity, model: RawWeatherModel) {
        assert(entity.timedForecasts[0].date == model.timedForecasts[0].date)
        assert(entity.timedForecasts[0].attrs.temp == model.timedForecasts[0].attrs.temp)
        assert(entity.timedForecasts[0].attrs.tempMin == model.timedForecasts[0].attrs.tempMin)
        assert(entity.timedForecasts[0].attrs.tempMax == model.timedForecasts[0].attrs.tempMax)
        assert(entity.timedForecasts[0].attrs.pressure == model.timedForecasts[0].attrs.pressure)
    }
}