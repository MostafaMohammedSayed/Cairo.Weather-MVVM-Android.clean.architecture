package com.mostafamohammed.remote

import com.mostafamohammed.remote.apiService.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: ApiService = retrofit.create(ApiService::class.java)
}