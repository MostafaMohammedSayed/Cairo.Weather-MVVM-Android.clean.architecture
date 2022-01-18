package com.mostafamohammed.domain.useCases

import com.mostafamohammed.domain.executer.PostExecutionThread
import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.models.TimedForecast
import com.mostafamohammed.domain.repository.WeatherForecastRepository
import com.mostafamohammed.domain.usecases.checkForecast.GetForecast
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetForecastTestByMockito {

    private lateinit var getForecast: GetForecast

    @Mock
    lateinit var forecastRepository: WeatherForecastRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    val rawWeather = RawWeather(
        timedForecasts = listOf(
            TimedForecast(
                attrs = ForecastAttributes(
                    temp = 25.0,
                    tempMin = 13.0,
                    tempMax = 27.0,
                    pressure = 1000.1
                ),
                date = "25-11-2159"
            )
        )
    )

    @Before
    fun setUP() {
        MockitoAnnotations.openMocks(this)
        getForecast = GetForecast(forecastRepository, postExecutionThread)
    }

    @Test
    fun getForecastCompletes() {
        stubGetForecast(Observable.just(ForecastDataFactory.makeARawWaether()))

        val testObserver = getForecast.buildUseCaseObservable(ForecastDataFactory.makeAParam()).test()
        testObserver.assertComplete()
    }

    @Test
    fun getForecastReturnsData() {

        val model = ForecastDataFactory.makeARawWaether()

        stubGetForecast(Observable.just(model))

        val testObserver = getForecast.buildUseCaseObservable(ForecastDataFactory.makeAParam()).test()
        testObserver.assertValue(model)
    }

    @Test
    fun getForecastThrowsException() {
        stubGetForecast(Observable.just(ForecastDataFactory.makeARawWaether()))

        //WHEN params = null an exception is thrown
        Assert.assertThrows(IllegalArgumentException::class.java) {
            getForecast.buildUseCaseObservable(null)
        }
    }

    private fun stubGetForecast(observable: Observable<RawWeather>) {
        whenever(forecastRepository.getForecast("metric", "apiKey"))
            .thenReturn(observable)
    }
}