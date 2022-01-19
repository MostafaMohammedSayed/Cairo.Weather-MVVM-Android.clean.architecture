package com.mostafamohammed.remote.mappers

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.remote.RemoteLayerModelFactory
import com.mostafamohammed.remote.models.ForecastAttributesModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ForecastAttributesModelMapperTest {
    private val mapper = ForecastAttributesModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = RemoteLayerModelFactory.makeAttrsModel()
        val entity = mapper.mapFromModel(model)
        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: ForecastAttributesEntity, model: ForecastAttributesModel) {
        assert(entity.temp == model.temp)
        assert(entity.tempMin == model.tempMin)
        assert(entity.tempMax == model.tempMax)
        assert(entity.pressure == model.pressure)
    }
}