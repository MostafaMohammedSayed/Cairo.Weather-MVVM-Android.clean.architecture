package com.mostafamohammed.remote

import com.mostafamohammed.data.models.ForecastAttributesEntity
import com.mostafamohammed.data.models.RawWeatherEntity
import com.mostafamohammed.data.models.TimedForecastEntity
import com.mostafamohammed.remote.models.ForecastAttributesModel
import com.mostafamohammed.remote.models.RawWeatherModel
import com.mostafamohammed.remote.models.TimedForecastModel

object RemoteLayerModelFactory {
    fun makeRawWeatherModel(): RawWeatherModel {
        return RawWeatherModel(
            timedForecasts = makeTimedForecastModels()
        )
    }

    fun makeTimedForecastModels(): List<TimedForecastModel> {
        val timedForecasts = mutableListOf<TimedForecastModel>()
        repeat(2) {
            timedForecasts.add(
                TimedForecastModel(
                    attrs = makeAttrsModel(),
                    date = RemoteLayerDataFactory.randomString()
                )
            )
        }
        return timedForecasts
    }

    fun makeTimedForecastModel(): TimedForecastModel {
        return TimedForecastModel(
                    attrs = makeAttrsModel(),
                    date = RemoteLayerDataFactory.randomString()
                )
        }


    fun makeAttrsModel(): ForecastAttributesModel {
        return ForecastAttributesModel(
            temp = RemoteLayerDataFactory.randomDouble(),
            tempMin = RemoteLayerDataFactory.randomDouble(),
            tempMax = RemoteLayerDataFactory.randomDouble(),
            pressure = RemoteLayerDataFactory.randomDouble()
        )
    }

    fun makeRawWeatherEntity(): RawWeatherEntity {
        return RawWeatherEntity(
            timedForecasts = makeTimedForecastsEntity()
        )
    }

    fun makeTimedForecastsEntity(): List<TimedForecastEntity> {
        val timedForecasts = mutableListOf<TimedForecastEntity>()
        repeat(2) {
            timedForecasts.add(
                TimedForecastEntity(
                    attrs = makeAttrsEntity(),
                    date = RemoteLayerDataFactory.randomString()
                )
            )
        }
        return timedForecasts
    }

    fun makeAttrsEntity(): ForecastAttributesEntity {
        return ForecastAttributesEntity(
            temp = RemoteLayerDataFactory.randomDouble(),
            tempMin = RemoteLayerDataFactory.randomDouble(),
            tempMax = RemoteLayerDataFactory.randomDouble(),
            pressure = RemoteLayerDataFactory.randomDouble()
        )
    }

    fun fromEntityToModel(entity: RawWeatherEntity): RawWeatherModel {
        return RawWeatherModel(
            timedForecasts = entity.timedForecasts.map { timedForecastEntity ->
                fromTimedForecastEntityToTimedForecastModel(timedForecastEntity)
            }
        )
    }

    private fun fromTimedForecastEntityToTimedForecastModel(timedForecastEntity: TimedForecastEntity): TimedForecastModel {
        return TimedForecastModel(
            attrs = ForecastAttributesModel(
                temp = timedForecastEntity.attrs.temp,
                tempMin = timedForecastEntity.attrs.tempMin,
                tempMax = timedForecastEntity.attrs.tempMax,
                pressure = timedForecastEntity.attrs.pressure
            ),
            date = timedForecastEntity.date
        )
    }

    fun fromModelToEntity(model: ForecastAttributesModel): ForecastAttributesEntity {
        return ForecastAttributesEntity(
            temp = model.temp,
            tempMin = model.tempMin,
            tempMax = model.tempMax,
            pressure = model.pressure,
        )
    }

    fun fromTimedForecastModelToTimedForecastEntity(timedForecastModel: TimedForecastModel): TimedForecastEntity {
        return TimedForecastEntity(
            attrs = fromModelToEntity(timedForecastModel.attrs)
        , date =  timedForecastModel.date
        )
    }
}