package com.example.k_01.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.k_01.databinding.FragmentWeatherListBinding
import com.example.k_01.repository.Weather

//Наш фрагмент
class DetailsFragment : Fragment() {

    // создаем livedata
    private var _binding:FragmentWeatherListBinding?=null
    private val binding:FragmentWeatherListBinding
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
    ): View? {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)

        //return inflater.inflate(R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.
    }

    //По ответу :55 формируем внешний вид приложения
    /*
    private fun renderData(data:AppState){

        when(data){
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                //binding.message.text = "НЕ получилось ${data.error}"
                Snackbar.make(binding.mainView, "НЕ получилось ${data.error}", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                //пошла загрузка (ProgressBar)
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {

                binding.loadingLayout.visibility = View.GONE
                //отображение результата
               // binding.message.text = "Получилось!"
                binding.cityName.text=data.weatherData.сity.name.toString()
                binding.temperatureValue.text = data.weatherData.temperature.toString()
                binding.feelsLikeValue.text = data.weatherData.feelsLike.toString()
                binding.cityCoordinates.text = "${data.weatherData.сity.lat} ${data.weatherData.сity.lon}"
                Snackbar.make(binding.mainView, "Получилось", Snackbar.LENGTH_LONG).show()
               // Toast.makeText(requireContext(),"Работает", Toast.LENGTH_SHORT).show()
            }

        }
    }
*/
    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle):DetailsFragment {

            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}