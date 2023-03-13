package com.example.k_01.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.k_01.databinding.FragmentDetailsBinding
import com.example.k_01.repository.Weather
import com.example.k_01.utils.*
import com.example.k_01.viewmodel.DetailsState
import com.example.k_01.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar

//фрагмент viewModel
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
        return binding.root
    }

    private val viewModel:DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//Фрагмент получает viewModel
        viewModel.getLiveData().observe(viewLifecycleOwner,object :Observer<DetailsState>{
            override fun onChanged(t: DetailsState) {
                //отрисовка LiveData
                renderData(t)
            }
        })


        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let{
            viewModel.getWeather(it.сity)
        }

    }



    @SuppressLint("SetTextI18n")
    private fun renderData(detailsState: DetailsState){

        when(detailsState){
            is DetailsState.Error -> TODO()
            DetailsState.Loading -> TODO()
            is DetailsState.Success -> {
                val weather = detailsState.weather
                with(binding){
                    loadingLayout.visibility = View.GONE
                    cityName.text = weather.сity.name
                    temperatureValue.text = weather.temperature.toString()
                    feelsLikeValue.text = weather.feelsLike.toString()
                    cityCoordinates.text= "${weather.сity.lat} ${weather.сity.lon}"
                    Snackbar.make(mainView,"ПОЛУЧИЛОСЬ", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }


    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle):DetailsFragment {

            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}