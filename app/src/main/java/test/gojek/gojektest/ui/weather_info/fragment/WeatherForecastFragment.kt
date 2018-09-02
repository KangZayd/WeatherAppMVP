package test.gojek.gojektest.ui.weather_info.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.weather_info_screen.*
import test.gojek.gojektest.R
import test.gojek.gojektest.data.response.WeatherInfo
import test.gojek.gojektest.ui.base.BaseFragment
import test.gojek.gojektest.ui.weather_info.adapter.WeatherForecastAdapter
import test.gojek.gojektest.util.addDegreeSuperScript

class WeatherForecastFragment : BaseFragment() {

    lateinit var weatherInfo : WeatherInfo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.weather_info_screen,container,false)
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