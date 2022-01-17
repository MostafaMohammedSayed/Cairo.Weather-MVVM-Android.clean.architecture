package com.mostafamohammed.mobileui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mostafamohammed.mobileui.R
import com.mostafamohammed.presentation.model.TimedForecastView
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherAdapter :
    ListAdapter<TimedForecastView, WeatherAdapter.WeatherViewHolder>(TimedForecastDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val forecast = getItem(position)
        holder.bind(forecast)
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(forecast: TimedForecastView) {
            itemView.tvDate.text = forecast.date
            itemView.tvTemp.text = forecast.attrs.temp.toString()
            itemView.tvTempMin.text = forecast.attrs.tempMin.toString()
            itemView.tvTempMax.text = forecast.attrs.tempMax.toString()
            itemView.tvPress.text = forecast.attrs.pressure.toString()
            val coldTemp = 15
            if (forecast.attrs.temp <= coldTemp) {
                itemView.tempIcon.setImageResource(R.drawable.ic_baseline_ac_unit_24)
            }
        }
    }

    class TimedForecastDiffUtil : DiffUtil.ItemCallback<TimedForecastView>() {
        override fun areItemsTheSame(
            oldItem: TimedForecastView,
            newItem: TimedForecastView
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TimedForecastView,
            newItem: TimedForecastView
        ): Boolean {
            return oldItem.date == newItem.date
        }
    }
}