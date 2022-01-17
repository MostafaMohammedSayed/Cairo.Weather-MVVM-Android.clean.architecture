package com.mostafamohammed.remote.apiService

import com.mostafamohammed.remote.models.RawWeatherModel
import com.mostafamohammed.remote.remoteUtils.NetworkConstants.WEATHER_API_PARAM
import com.mostafamohammed.remote.remoteUtils.NetworkConstants.WEATHER_END_POINT
import com.mostafamohammed.remote.remoteUtils.NetworkConstants.WEATHER_UNITS_PARAM
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(WEATHER_END_POINT)
    fun getWeather(
        @Query(WEATHER_UNITS_PARAM) units: String,
        @Query(WEATHER_API_PARAM) appid: String
    ): Observable<RawWeatherModel>
}