package com.mostafamohammed.data.mapper

import com.mostafamohammed.data.ModelFactory
import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.domain.models.ForecastAttributes
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ForecastAttributesMapperTest {

    val mapper = ForecastAttributesMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = ModelFactory.makeAttrsEntity()
        val resultModel = mapper.mapFromEntity(entity)
        assertEqualData(entity, resultModel)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = ModelFactory.makeAttrs()
        val entity = mapper.mapToEntity(model)
        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: ForecastAttributesEntity, model: ForecastAttributes) {
        assert(entity.temp == model.temp)
        assert(entity.tempMin == model.tempMin)
        assert(entity.tempMax == model.tempMax)
        assert(entity.pressure == model.pressure)
    }
}