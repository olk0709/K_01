package com.example.k_01.view.details

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.k_01.databinding.FragmentDetailsBinding
import com.example.k_01.repository.Weather
import com.example.k_01.repository.dto.WeatherDTO
import com.example.k_01.utils.*
import com.example.k_01.viewmodel.DetailsState
import com.example.k_01.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import javax.net.ssl.HttpsURLConnection

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
       // LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    /*
    private   val receiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
                intent?.let { intent ->
                    intent.getParcelableExtra<WeatherDTO>(KEY_BUNDLE_SERVICE_BROADCAST_WEATHER)?.let {
                        onResponse(it)
                    }
                }
        }

    }*/


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

                    /*Glide.with(requireContext())
                        .load("https://freepngimg.com/thmb/city/36275-3-city-hd.png")
                        .into(headerIcon)*/

                   /* Picasso.get()?.load("https://freepngimg.com/thmb/city/36275-3-city-hd.png")
                    ?.into(headerIcon) */

                    //headerCityIcon.load("https://freepngimg.com/thmb/city/36275-3-city-hd.png")
                    icon.loadSvg("https://yastatic..net/weather/i/icons/blueye/color/svg${weather.icon}.svg")


                }
            }
        }


    }
    fun  ImageView.loadSvg(url: String){
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry{add(SvgDecoder(this@loadSvg.context))}
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)

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