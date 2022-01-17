package com.mostafamohammed.data.repository

import com.mostafamohammed.data.models.RawWeatherEntity
import io.reactivex.rxjava3.core.Observable

/*this interface is intended to be implemented by the remote layer
*in case the app adds a feature of a cache layer another interface
* is needed to be implemented by this cache layer
 */
interface RawWeatherRemote {
    fun getForecast(units: String, apiKey: String): Observable<RawWeatherEntity>
}