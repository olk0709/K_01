package com.example.k_01.view.weatherlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_01.R
import com.example.k_01.databinding.FragmentWeatherListBinding
import com.example.k_01.repository.Weather
import com.example.k_01.utils.KEY_BUNDLE_WEATHER
import com.example.k_01.view.details.DetailsFragment
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

private   val adapter = WeatherListAdapter(this)

    override fun onDestroy(){
        super.onDestroy()
        _binding = null   //нашли, как занулить(
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)

        //return inflater.inflate(R.layout.fragment_main, container, false)
        return binding.root
    }

    var isRussian = true

    private   val viewModel:MainViewModel by lazy {
       ViewModelProvider(this).get(MainViewModel::class.java)
   }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.recyclerView.adapter = adapter
        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }



        // создаем livedata если ее не существует с с пом-ю ViewModelProvider

        val observer = {data: AppState -> renderData(data)}
        viewModel.getData().observe(viewLifecycleOwner, observer)
        //в viewModel посылаем запрос на погоду goto: 55

        binding.floatingActionButton.setOnClickListener{
            isRussian = !isRussian
            if(isRussian){
                viewModel.getWeatherRussia()
                binding.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_russia))
            }else{
                binding.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_earth))
                viewModel.getWeatherWorld()
            }

        }
        viewModel.getWeatherRussia()
    }

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
                adapter.setData(data.weatherList)
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }

    override fun onItemClick(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction().add(
            R.id.container,
            DetailsFragment.newInstance(Bundle().apply {
                putParcelable(KEY_BUNDLE_WEATHER, weather)
            })
        ).addToBackStack("").commit()

    }
}