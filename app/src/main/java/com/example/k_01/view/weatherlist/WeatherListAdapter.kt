package com.example.k_01.view.weatherlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.k_01.databinding.FragmentWeatherListRecyclerItemBinding
import com.example.k_01.repository.Weather

class WeatherListAdapter(private var data:List<Weather> = listOf()):RecyclerView.Adapter<WeatherListAdapter.CityHolder>() {

    fun setData(dataNew:List<Weather>){
        this.data = dataNew
        notifyDataSetChanged()   //Можно DiffUtil чтобы работать быстрее

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val binding = FragmentWeatherListRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount()=data.size

    class CityHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(weather: Weather){
            val binding = FragmentWeatherListRecyclerItemBinding.bind(itemView)   //.apply {  }
            binding.tvcityName.text = weather.сity.name
        }
    }
}