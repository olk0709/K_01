package com.example.k_01.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.k_01.R
import com.example.k_01.databinding.FragmentWeatherListRecyclerItemBinding
import com.example.k_01.repository.Weather
import com.example.k_01.utils.KEY_BUNDLE_WEATHER
import com.example.k_01.view.MainActivity
import com.example.k_01.view.details.DetailsFragment

class WeatherListAdapter(private val onItemListClickListener : OnItemListClickListener,
    private var data:List<Weather> = listOf()):
    RecyclerView.Adapter<WeatherListAdapter.CityHolder>() {

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

    inner class CityHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(weather: Weather){
            FragmentWeatherListRecyclerItemBinding.bind(itemView).apply {
                tvcityName.text = weather.сity.name
                root.setOnClickListener{
                    val bundle = Bundle()
                    bundle.putParcelable(KEY_BUNDLE_WEATHER, weather)
                    (itemView.context as MainActivity).supportFragmentManager.beginTransaction().add(
                        R.id.container,
                        DetailsFragment.newInstance(bundle)).addToBackStack("").commit()
                    onItemListClickListener.onItemClick(weather)
                }
            }
        }
    }
}