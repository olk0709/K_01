package com.example.k_01.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.k_01.databinding.FragmentDetailsBinding
import com.example.k_01.databinding.FragmentWeatherListBinding
import com.example.k_01.repository.Weather
import com.example.k_01.repository.WeatherDTO
import com.example.k_01.repository.WeatherLoader
import com.example.k_01.utils.KEY_BUNDLE_WEATHER
import com.google.android.material.snackbar.Snackbar

//Наш фрагмент
class DetailsFragment : Fragment() {

    // создаем livedata
    private var _binding:FragmentDetailsBinding?=null
    private val binding:FragmentDetailsBinding
    get() {
        return _binding!!
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null   //нашли, как занулить(
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        //return inflater.inflate(R.layout.fragment_main, container, false)
        return binding.root
    }
    lateinit var currentCityName:String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let{
            currentCityName = it.сity.name
          renderData(WeatherLoader().loadWeather(it.сity.lat, it.сity.lon))
        //renderData(it)
        }

        //val weather:Weather = requireArguments().getParcelable<Weather>(KEY_BUNDLE_WEATHER)!!
        //renderData(weather)
    }

    //По ответу :55 формируем внешний вид приложения

    private fun renderData(weather: WeatherDTO){
        with(binding){
            loadingLayout.visibility = View.GONE
            cityName.text= currentCityName
            with(weather){
                temperatureValue.text = weather.factDTO.temperature.toString()
                feelsLikeValue.text = weather.factDTO.feels_like.toString()
                cityCoordinates.text= "${weather.infoDTO.lat} ${weather.infoDTO.lon}"
            }
            //Snackbar.make(mainView, "Получилось", Snackbar.LENGTH_LONG).show()
            //mainView.showSnackBar()
        }
    }

    /*private fun renderData(weather:WeatherDTO){
        binding.loadingLayout.visibility = View.GONE
        binding.cityName.text=localWeather.сity.name.toString()
        binding.temperatureValue.text = weather.factDTO.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
        binding.cityCoordinates.text = "${weather.сity.lat} ${weather.сity.lon}"
        //Snackbar.make(binding.mainView, "Получилось", Snackbar.LENGTH_LONG).show()
        // Toast.makeText(requireContext(),"Работает", Toast.LENGTH_SHORT).show()
    }*/

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle):DetailsFragment {

            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}