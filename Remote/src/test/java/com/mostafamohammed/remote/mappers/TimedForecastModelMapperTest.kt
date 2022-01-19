package com.mostafamohammed.remote.mappers

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.remote.RemoteLayerModelFactory
import com.mostafamohammed.remote.models.ForecastAttributesModel
import com.mostafamohammed.remote.models.TimedForecastModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class TimedForecastModelMapperTest {
    @Mock private lateinit var forecastAttributesModelMapper: ForecastAttributesModelMapper
    private lateinit var mapper: TimedForecastModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mapper = TimedForecastModelMapper(forecastAttributesModelMapper)
    }

    private fun stubForecastAttributesModelMapperMapFromModel(model: ForecastAttributesModel) {
        whenever(forecastAttributesModelMapper.mapFromModel(model))
            .thenReturn(
                ForecastAttributesEntity(
                temp = model.temp,
                tempMin = model.tempMin,
                tempMax = model.tempMax,
                pressure = model.pressure
            )
            )
    }

    @Test
    fun mapFromModelMapsData() {
        val timedForecastModel = RemoteLayerModelFactory.makeTimedForecastModel()

        stubForecastAttributesModelMapperMapFromModel(timedForecastModel.attrs)
        val entity = mapper.mapFromModel(timedForecastModel)
        assertEqualData(entity,timedForecastModel)
    }

    private fun assertEqualData(entity: TimedForecastEntity, model: TimedForecastModel) {
        assert(entity.date == model.date)
        assert(entity.attrs.temp == model.attrs.temp)
        assert(entity.attrs.tempMin == model.attrs.tempMin)
        assert(entity.attrs.tempMax == model.attrs.tempMax)
        assert(entity.attrs.pressure == model.attrs.pressure)
    }
}