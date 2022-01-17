package com.mostafamohammed.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.usecases.checkForecast.GetForecast
import com.mostafamohammed.presentation.base.Resource
import com.mostafamohammed.presentation.factory.RawWeatherFactory
import com.mostafamohammed.presentation.mapper.RawWeatherViewMapper
import com.mostafamohammed.presentation.model.RawWeatherView
import io.reactivex.rxjava3.observers.DisposableObserver
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.kotlin.*
import java.lang.RuntimeException

@RunWith(JUnit4::class)
class HomeViewModelTest{

    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var getForecast = mock<GetForecast>()
    var mapper = mock<RawWeatherViewMapper>()
    var params = mock<GetForecast.Params>()
    var homeViewModel = mock<HomeViewModel>()
    var observer = mock<DisposableObserver<RawWeather>>()

    @Captor
    val captor = argumentCaptor<DisposableObserver<RawWeather>>()


    @Test
    fun fetchForecastExecutesUseCase(){
        homeViewModel.fetchForecast("metric","660a1bcf9fb3c5e90d06039601866186")

        verify(getForecast, times(1)).execute(captor.firstValue,params)
    }

    @Test
    fun fetchForecastReturnsSuccess(){
        val rawWeather = RawWeatherFactory.makeRawWeather()
        val rawWeatherView = mapper.mapToView(rawWeather)

        stubRawWeatherViewMapperMapToView(rawWeatherView,rawWeather)
        homeViewModel.fetchForecast("metric","660a1bcf9fb3c5e90d06039601866186")

        verify(getForecast).execute(captor.capture(),params)
        captor.firstValue.onNext(rawWeather)
        assertEquals(Resource.success(rawWeather),homeViewModel.getForecast().value?.state)
    }

    private fun stubRawWeatherViewMapperMapToView(rawWeatherView: RawWeatherView,
                                                  rawWeather: RawWeather){
        whenever(mapper.mapToView(rawWeather))
            .thenReturn(rawWeatherView)
    }

    @Test
    fun fetForecastReturnsData(){
        val rawWeather = RawWeatherFactory.makeRawWeather()
        val rawWeatherView = mapper.mapToView(rawWeather)

        stubRawWeatherViewMapperMapToView(rawWeatherView,rawWeather)

        homeViewModel.fetchForecast("metric","660a1bcf9fb3c5e90d06039601866186")

        verify(getForecast).execute(captor.capture(),params)
        captor.firstValue.onNext(rawWeather)
        assertEquals(Resource.success(rawWeatherView),homeViewModel.getForecast().value?.value)
    }

    @Test
    fun fetchForecastReturnsError(){
        val rawWeather = RawWeatherFactory.makeRawWeather()
        val rawWeatherView = mapper.mapToView(rawWeather)

        stubRawWeatherViewMapperMapToView(rawWeatherView,rawWeather)

        homeViewModel.fetchForecast("metric","660a1bcf9fb3c5e90d06039601866186")

        verify(getForecast).execute(captor.capture(),params)
        captor.firstValue.onError(RuntimeException())
        assertEquals(Resource(null, Throwable(),Resource.State.ERROR),homeViewModel.getForecast().value?.state)
    }
}