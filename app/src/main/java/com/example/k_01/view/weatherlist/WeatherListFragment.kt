package com.example.k_01.view.weatherlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.k_01.R
import com.example.k_01.databinding.FragmentWeatherListBinding
import com.example.k_01.repository.Weather
import com.example.k_01.utils.KEY_BUNDLE_WEATHER
import com.example.k_01.viewmodel.AppState
import com.example.k_01.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

//Наш фрагмент
class WeatherListFragment : Fragment(), OnItemListClickListener {

    // создаем livedata
    private var _binding:FragmentWeatherListBinding?=null
    private val binding:FragmentWeatherListBinding
    get() {
        return _binding!!
    }

    val adapter = WeatherListAdapter(this)

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

    var isRussian = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  binding.btnOne.setOnClickListener{}
       // view.findViewById<Button>(R.id.btnOne).setOnClickListener{}

        binding.recyclerView.adapter = adapter

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
        binding.floatingActionButton.setOnClickListener{
            isRussian = !isRussian
            if(isRussian){
                viewModel.getWeatherRussia()
                binding.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_russia))
                    //resources.getDrawable(R.drawable.ic_russia) не подходит, т.к. работаем с текущим контекстом
            }else{
                binding.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_earth))
                viewModel.getWeatherWorld()
            }

        }
        viewModel.getWeatherRussia()
    }

    //По ответу :55 формируем внешний вид приложения
    private fun renderData(data:AppState){

        when(data){
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                //binding.message.text = "НЕ получилось ${data.error}"
                Snackbar.make(binding.root, "НЕ получилось ${data.error}", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                //пошла загрузка (ProgressBar)
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {

                binding.loadingLayout.visibility = View.GONE
                //data.weatherList
                adapter.setData(data.weatherList)

                //отображение результата
               // binding.message.text = "Получилось!"
                /*binding.cityName.text=data.weatherData.сity.name.toString()
                binding.temperatureValue.text = data.weatherData.temperature.toString()
                binding.feelsLikeValue.text = data.weatherData.feelsLike.toString()
                binding.cityCoordinates.text = "${data.weatherData.сity.lat} ${data.weatherData.сity.lon}"
                Snackbar.make(binding.mainView, "Получилось", Snackbar.LENGTH_LONG).show()

                 */
               // Toast.makeText(requireContext(),"Работает", Toast.LENGTH_SHORT).show()
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }

    override fun onItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(KEY_BUNDLE_WEATHER, weather)
        requireActivity().supportFragmentManager.beginTransaction().add(
            R.id.container,
            DefaultFragment.newInstance(bundle)
        ).addToBackStack("").commit()

    }
}