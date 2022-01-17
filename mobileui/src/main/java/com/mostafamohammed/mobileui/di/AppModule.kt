package com.mostafamohammed.mobileui.di


import com.mostafamohammed.data.mapper.ForecastAttributesMapper
import com.mostafamohammed.data.mapper.RawWeatherMapper
import com.mostafamohammed.data.mapper.TimedForecastMapper
import com.mostafamohammed.data.sources.RawWeatherDataSource
import com.mostafamohammed.data.sources.RawWeatherRemoteDataSource
import com.mostafamohammed.domain.executer.PostExecutionThread
import com.mostafamohammed.domain.repository.WeatherForecastRepository
import com.mostafamohammed.domain.usecases.checkForecast.GetForecast
import com.mostafamohammed.mobileui.BuildConfig.BASE_URL
import com.mostafamohammed.mobileui.UIThread
import com.mostafamohammed.presentation.mapper.ForecastAttributesViewMapper
import com.mostafamohammed.presentation.mapper.RawWeatherViewMapper
import com.mostafamohammed.presentation.mapper.TimedForecastViewMapper
import com.mostafamohammed.remote.RawWeatherRemoteImpl
import com.mostafamohammed.remote.apiService.ApiService
import com.mostafamohammed.remote.mappers.ForecastAttributesModelMapper
import com.mostafamohammed.remote.mappers.RawWeatherModelMapper
import com.mostafamohammed.remote.mappers.TimedForecastModelMapper
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideRetrofit() }
    single { provideApiService(get()) }
    factory { provideTimedForecastModelMapper(get()) }
    factory { provideForecastAttributesModelMapper() }
    factory { provideRawWeatherModelMapper(get()) }
    single { provideRawWeatherRemoteImpl(get(), get()) }
    single { provideRawWeatherRemoteDataSource(get()) }
    factory { provideTimedForecastMapper(get()) }
    factory { provideForecastAttributesMapper() }
    factory { provideRawWeatherMapper(get()) }
    //single { provideRawWeatherDataSource(get(), get()) }
    factory { provideTimedForecastViewMapper(get()) }
    factory { provideForecastAttributesViewMapper() }
    factory { provideRawWeatherViewMapper(get()) }
    single { providePostExecutionThread() }
    single { provideGetForecast(get(), get()) }
}

fun provideGetForecast(
    forecastRepository: WeatherForecastRepository,
    postExecutionThread: PostExecutionThread
): GetForecast =
    GetForecast(forecastRepository, postExecutionThread)


fun providePostExecutionThread(): PostExecutionThread = UIThread()

fun provideRawWeatherViewMapper(
    timedForecastViewMapper: TimedForecastViewMapper
): RawWeatherViewMapper = RawWeatherViewMapper(timedForecastViewMapper)

fun provideForecastAttributesViewMapper(): ForecastAttributesViewMapper =
    ForecastAttributesViewMapper()

fun provideTimedForecastViewMapper(forecastAttributesViewMapper: ForecastAttributesViewMapper): TimedForecastViewMapper =
    TimedForecastViewMapper(forecastAttributesViewMapper)

fun provideRawWeatherDataSource(
    rawWeatherMapper: RawWeatherMapper,
    rawWeatherRemoteDataSource: RawWeatherRemoteDataSource
): RawWeatherDataSource = RawWeatherDataSource(
    rawWeatherMapper, rawWeatherRemoteDataSource
)

fun provideRawWeatherMapper(
    timedForecastMapper: TimedForecastMapper
): RawWeatherMapper = RawWeatherMapper(timedForecastMapper)

fun provideForecastAttributesMapper(): ForecastAttributesMapper =
    ForecastAttributesMapper()

fun provideTimedForecastMapper(forecastAttributesMapper: ForecastAttributesMapper): TimedForecastMapper =
    TimedForecastMapper(forecastAttributesMapper)

fun provideRawWeatherRemoteDataSource(rawWeatherRemoteImpl: RawWeatherRemoteImpl): RawWeatherRemoteDataSource =
    RawWeatherRemoteDataSource(rawWeatherRemoteImpl)

fun provideRawWeatherRemoteImpl(
    service: ApiService,
    mapper: RawWeatherModelMapper
): RawWeatherRemoteImpl = RawWeatherRemoteImpl(service, mapper)

fun provideRawWeatherModelMapper(
    timedForecastModelMapper: TimedForecastModelMapper
): RawWeatherModelMapper = RawWeatherModelMapper(timedForecastModelMapper)

fun provideForecastAttributesModelMapper(): ForecastAttributesModelMapper =
    ForecastAttributesModelMapper()

fun provideTimedForecastModelMapper(forecastAttributesModelMapper: ForecastAttributesModelMapper): TimedForecastModelMapper =
    TimedForecastModelMapper(forecastAttributesModelMapper)

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
