package com.example.k_01.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.k_01.R
import com.example.k_01.databinding.FragmentHistoryWeatherListBinding
import com.example.k_01.databinding.FragmentHistoryWeatherListRecyclerItemBinding
import com.example.k_01.databinding.FragmentWeatherListRecyclerItemBinding
import com.example.k_01.repository.Weather
import com.example.k_01.utils.KEY_BUNDLE_WEATHER
import com.example.k_01.view.MainActivity
import com.example.k_01.view.details.DetailsFragment

class HistoryWeatherListAdapter(
                                private var data:List<Weather> = listOf()
):
    RecyclerView.Adapter<HistoryWeatherListAdapter.CityHolder>() {

    fun setData(dataNew:List<Weather>){
        this.data = dataNew
        notifyDataSetChanged()   //Можно DiffUtil чтобы работать быстрее

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val binding = FragmentHistoryWeatherListRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CityHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount()=data.size

    inner class CityHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(weather: Weather){
            FragmentHistoryWeatherListRecyclerItemBinding.bind(itemView).apply {
                tvcityName.text = weather.сity.name
                tvTemperature.text = weather.temperature.toString()
                tvFeelsLike.text = weather.feelsLike.toString()
            }
        }
    }
}