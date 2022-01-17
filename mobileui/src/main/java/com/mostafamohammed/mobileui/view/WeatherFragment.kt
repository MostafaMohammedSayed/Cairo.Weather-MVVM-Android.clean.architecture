package com.mostafamohammed.mobileui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mostafamohammed.mobileui.BuildConfig
import com.mostafamohammed.mobileui.R
import com.mostafamohammed.mobileui.adapter.WeatherAdapter
import com.mostafamohammed.presentation.base.Resource
import com.mostafamohammed.presentation.viewModel.HomeViewModel
import com.mostafamohammed.remote.remoteUtils.NetworkConstants
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {
    private val forecastsAdapter = WeatherAdapter()
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        fetchWeather()
        startWeatherObserver()
    }

    private fun setUpRecyclerView(){
        rvWeather.adapter = forecastsAdapter

        val divider = DividerItemDecoration(
            rvWeather.context, RecyclerView.VERTICAL
        )
        rvWeather.addItemDecoration(divider)
    }

    private fun fetchWeather() {
        homeViewModel.fetchForecast(NetworkConstants.WEATHER_UNITS_TYPE, BuildConfig.API_KEY)
    }

    private fun startWeatherObserver() {
        homeViewModel.observeWeather(this,{rawWeatherViewResource->
            if (rawWeatherViewResource.state == Resource.State.SUCCESS){
                forecastsAdapter.submitList(rawWeatherViewResource.value?.timedForecasts)
                loadingIndicator.visibility = View.GONE
            }else if (rawWeatherViewResource.state == Resource.State.ERROR){
                Log.e(this.javaClass.simpleName,rawWeatherViewResource.throwable?.message,rawWeatherViewResource.throwable)
                loadingIndicator.visibility = View.GONE
                tvError.visibility = View.VISIBLE
            }
        })
    }
}