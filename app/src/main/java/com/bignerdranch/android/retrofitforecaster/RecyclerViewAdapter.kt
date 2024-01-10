package com.bignerdranch.android.retrofitforecaster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : ListAdapter<WeatherForecast, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherForecast>() {
            override fun areItemsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
                return oldItem.dt_txt == newItem.dt_txt
            }

            override fun areContentsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val temp = getItem(position).main.temp
        return if (temp < 0) R.layout.retrofit_cold else R.layout.retrofit_hot
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return if (viewType == R.layout.retrofit_hot) ViewHolderHot(view) else ViewHolderCold(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val forecast = getItem(position)
        if (holder is ViewHolderHot) {
            holder.bind(forecast)
        } else if (holder is ViewHolderCold) {
            holder.bind(forecast)
        }
    }
}