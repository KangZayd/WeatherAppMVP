package test.demo.weatherapp.ui.weather_info.fragment

import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.weather_info_screen.*
import test.demo.weatherapp.R
import test.demo.weatherapp.data.response.WeatherInfo
import test.demo.weatherapp.ui.base.BaseFragment
import test.demo.weatherapp.ui.weather_info.adapter.WeatherForecastAdapter
import test.demo.weatherapp.util.addDegreeSuperScript

class WeatherForecastFragment : BaseFragment() {

    lateinit var weatherInfo : WeatherInfo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.weather_info_screen,container,false)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.setStatusBarColor(getResources().getColor(R.color.forecast_fragment_color));
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDataToView()
    }

    fun addDataToView(){
        var adapter = WeatherForecastAdapter()
        weatherInfo = arguments?.get("weather_info") as WeatherInfo
        adapter.list =  weatherInfo.list
        rvForecastList.adapter = adapter
        rvForecastList.layoutManager = LinearLayoutManager(context)

        var currentWeather = weatherInfo.currentWeatherResponse
        tvCityName.text = currentWeather.location.name
        tvDegrees.text = addDegreeSuperScript(currentWeather.current.temp_c.toInt().toString())
    }

}