package com.example.k_01.view.details

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.k_01.databinding.FragmentDetailsBinding
import com.example.k_01.repository.OnServerResponse
import com.example.k_01.repository.Weather
import com.example.k_01.repository.dto.WeatherDTO
import com.example.k_01.utils.*

//Наш фрагмент
class DetailsFragment : Fragment(), OnServerResponse {

    // создаем livedata
    private var _binding:FragmentDetailsBinding?=null
    private val binding:FragmentDetailsBinding
    get() {
        return _binding!!
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null   //нашли, как занулить(
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    //создаем приемник
    val receiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?){
            intent?.let {intent ->
                intent.getParcelableExtra<WeatherDTO>(KEY_BUNDLE_SERVICE_BROADCAST_WEATHER)?.let {
                    onResponse(it)
                }

            }
        }
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

        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver,
        IntentFilter(KEY_WAVE_SERVICE_BROADCAST)
        )
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let{
            currentCityName = it.сity.name
            //Thread{
            //    WeatherLoader(this@DetailsFragment).loadWeather(it.сity.lat, it.сity.lon)
            //}.start()
            //старт сервиса вместо WeatherLoader
            requireActivity().startService(Intent(requireContext(),DetailsService::class.java).apply {
                putExtra(KEY_BUNDLE_LAT,it.сity.lat)
                putExtra(KEY_BUNDLE_LON,it.сity.lon)
            })

        //renderData(it)
        }

        //val weather:Weather = requireArguments().getParcelable<Weather>(KEY_BUNDLE_WEATHER)!!
        //renderData(weather)
    }

    //По ответу :55 формируем внешний вид приложения

    @SuppressLint("SetTextI18n")
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

    override fun onResponse(weatherDTO: WeatherDTO) {
        renderData(weatherDTO)
    }
}