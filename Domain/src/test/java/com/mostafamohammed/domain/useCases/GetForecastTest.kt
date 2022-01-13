package com.mostafamohammed.domain.useCases

import com.mostafamohammed.domain.executer.PostExecutionThread
import com.mostafamohammed.domain.models.ForecastAttributes
import com.mostafamohammed.domain.models.RawWeather
import com.mostafamohammed.domain.models.TimedForecast
import com.mostafamohammed.domain.usecases.checkForecast.GetForecast
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test


class GetForecastTest {

    private lateinit var rawWeather: RawWeather
    private lateinit var fakeForecastRepo: FakeWeatherForecastRepository
    private lateinit var thread: PostExecutionThread

    @Before
    fun setUp() {
        rawWeather = RawWeather(
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

        fakeForecastRepo = FakeWeatherForecastRepository(rawWeather)
        thread = object : PostExecutionThread {
            override val scheduler: Scheduler
                get() = Schedulers.io()
        }
    }

    @Test()
    fun buildUseCaseObservable_ExceptionThrowing() {

        //GIVEN an instance of test subject GetForecast
        val getForecast = GetForecast(fakeForecastRepo, thread)

        //WHEN PARAMS = NULL
        val params = null

        //THEN IllegalArgumentExceptionIsThrown
        assertThrows(IllegalArgumentException::class.java) {
            getForecast.buildUseCaseObservable(params)
        }
    }

    @Test()
    fun buildUseCaseObservable_Completes() {

        //GIVEN an instance of test subject GetForecast
        val getForecast = GetForecast(fakeForecastRepo, thread)

        //WHEN PARAMS are provided
        val params = GetForecast.Params("metric", "apiKey")

        //THEN observable<RawWeather> completes
        val testObserver = getForecast.buildUseCaseObservable(params).test()
        testObserver.assertComplete()
    }

    @Test()
    fun buildUseCaseObservable_valueAsExpected() {

        //GIVEN an instance of test subject GetForecast
        val getForecast = GetForecast(fakeForecastRepo, thread)

        //WHEN PARAMS are provided
        val params = GetForecast.Params("metric", "apiKey")

        //THEN observable<RawWeather> completes
        val testObserver = getForecast.buildUseCaseObservable(params).test()
        testObserver.assertValue(rawWeather)
    }
}