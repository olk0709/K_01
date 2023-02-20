package com.example.k_01.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.k_01.databinding.FragmentMainBinding
import com.example.k_01.viewmodel.AppState
import com.example.k_01.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

//Наш фрагмент
class MainFragment : Fragment() {

    // создаем livedata
    lateinit var binding:FragmentMainBinding

    override fun onDestroy(){
        super.onDestroy()
        //binding = null   надо найти, как занулить(
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        //return inflater.inflate(R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  binding.btnOne.setOnClickListener{}
       // view.findViewById<Button>(R.id.btnOne).setOnClickListener{}

        // создаем livedata если ее не существует с с пом-ю ViewModelProvider
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //val observer = Observer<Any>{renderData(it)}

        val observer = object: Observer<AppState> {
            override fun onChanged(data: AppState) {
                // получаем ответ на запрос :55
                renderData(data)
            }
        }

        //пробуем получить livedata . пробуем подписаться на  livedata
        viewModel.getData().observe(viewLifecycleOwner, observer)
        //в viewModel посылаем запрос на погоду goto: 55
        viewModel.getWeather()
    }

    //По ответу :55 формируем внешний вид приложения
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
               // binding.message.text = "Получилось"
                binding.temperatureValue.text = data.weatherData.temperature.toString()
                binding.feelsLikeValue.text = data.weatherData.feelsLike.toString()
                binding.cityCoordinates.text = "${data.weatherData.сity.lat} ${data.weatherData.сity.lon}"
                Snackbar.make(binding.mainView, "Получилось", Snackbar.LENGTH_LONG).show()
               // Toast.makeText(requireContext(),"Работает", Toast.LENGTH_SHORT).show()
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}