package com.mostafamohammed.mobileui.di

import com.mostafamohammed.data.repository.RawWeatherRemote
import com.mostafamohammed.data.sources.RawWeatherRepository
import com.mostafamohammed.domain.repository.WeatherForecastRepository
import com.mostafamohammed.remote.RawWeatherRemoteImpl
import org.koin.dsl.module

val repoModule = module{
    single<WeatherForecastRepository> { RawWeatherRepository(get(),get()) }
    single<RawWeatherRemote> { RawWeatherRemoteImpl(get(), get()) }
}