package com.mostafamohammed.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.usecases.checkForecast.GetForecast
import com.mostafamohammed.presentation.base.Resource
import com.mostafamohammed.presentation.factory.RawWeatherFactory
import com.mostafamohammed.presentation.factory.getOrAwaitValue
import com.mostafamohammed.presentation.mapper.RawWeatherViewMapper
import com.mostafamohammed.presentation.model.RawWeatherView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.just
import io.reactivex.rxjava3.observers.DisposableObserver
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*
import java.lang.RuntimeException
import java.util.*

@RunWith(JUnit4::class)
class HomeViewModelTest{


    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var homeViewModel: HomeViewModel
    @Mock private lateinit var getForecast: GetForecast
    @Mock private lateinit var mapper: RawWeatherViewMapper
    private val params = GetForecast.Params("metric","apiKey")
    @Captor
    lateinit var captor: ArgumentCaptor<DisposableObserver<RawWeather>>


    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        homeViewModel = HomeViewModel(getForecast,mapper)
    }

    @Test
    fun fetchForecastExecutesUseCase(){
        homeViewModel.fetchForecast(params.units,params.apiKey)

        verify(getForecast, times(1)).execute(homeViewModel.observer,params)
    }

    @Test
    fun fetchForecastReturnsSuccess(){
        val rawWeather = RawWeatherFactory.makeRawWeather()
        val rawWeatherView = RawWeatherFactory.makeRawWeatherView()

        stubRawWeatherViewMapperMapToView(rawWeatherView,rawWeather)
        homeViewModel.fetchForecast(params.units,params.apiKey)

        verify(getForecast).execute(homeViewModel.observer,params)
        assertEquals(Resource.success(rawWeatherView).state,homeViewModel.getForecast().getOrAwaitValue().state)
    }

    private fun stubRawWeatherViewMapperMapToView(rawWeatherView: RawWeatherView,
                                                  rawWeather: RawWeather){
        whenever(mapper.mapToView(rawWeather))
            .thenReturn(rawWeatherView)
    }

}