package test.gojek.gojektest.ui.weather_info

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.weather_info_screen.*
import test.gojek.gojektest.R
import test.gojek.gojektest.ui.base.BaseFragment

class WeatherForecastFragment : BaseFragment() {

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
        rvForecastList.adapter = adapter
        rvForecastList.layoutManager = LinearLayoutManager(context)
    }

}