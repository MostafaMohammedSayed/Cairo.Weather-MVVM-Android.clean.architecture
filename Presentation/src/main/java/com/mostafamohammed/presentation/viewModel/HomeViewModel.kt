package com.mostafamohammed.presentation.viewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.usecases.checkForecast.GetForecast
import com.mostafamohammed.presentation.base.Resource
import com.mostafamohammed.presentation.mapper.RawWeatherViewMapper
import com.mostafamohammed.presentation.model.RawWeatherView
import io.reactivex.rxjava3.observers.DisposableObserver

class HomeViewModel(
    private val getForecast: GetForecast,
    private val mapper: RawWeatherViewMapper
) : ViewModel() {

    private val forecastLiveData = MutableLiveData<Resource<RawWeatherView>>()

    fun getForecast(): MutableLiveData<Resource<RawWeatherView>> {
        return forecastLiveData
    }

    inner class ForecastSubscriber : DisposableObserver<RawWeather>() {
        override fun onNext(weather: RawWeather) {
            forecastLiveData.postValue(Resource.success(mapper.mapToView(weather)))
        }

        override fun onError(throwable: Throwable) {
            forecastLiveData.postValue(Resource.error(throwable))
        }

        override fun onComplete() {}

    }

    fun fetchForecast(unit: String, apiKey: String) {
        forecastLiveData.postValue(Resource.loading())
        val params = GetForecast.Params.createParams(unit, apiKey)
        return getForecast.execute(ForecastSubscriber(), params)
    }

    fun observeWeather(owner: LifecycleOwner, observer: Observer<Resource<RawWeatherView>>) {
        forecastLiveData.observe(owner, observer)
    }

    override fun onCleared() {
        getForecast.dispose()
        super.onCleared()
    }
}
