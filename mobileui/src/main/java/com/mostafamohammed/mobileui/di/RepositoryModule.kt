package com.mostafamohammed.mobileui.di

import com.mostafamohammed.data.repository.RawWeatherRemote
import com.mostafamohammed.data.sources.RawWeatherDataSource
import com.mostafamohammed.domain.repository.WeatherForecastRepository
import com.mostafamohammed.remote.RawWeatherRemoteImpl
import org.koin.dsl.module

val repoModule = module{
    single<WeatherForecastRepository> { RawWeatherDataSource(get(),get()) }
    single<RawWeatherRemote> { RawWeatherRemoteImpl(get(), get()) }
}