package test.gojek.gojektest.ui.weather_info

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.forecast_weather_row.*
import test.gojek.gojektest.ui.WeatherForecast

class WeatherForecastHolder(itemView : View) : RecyclerView.ViewHolder(itemView),LayoutContainer {
    override val containerView: View?
        get() = itemView

    fun bind( forecast: WeatherForecast){
        tvWeekName.text = forecast.days
        tvDegrees.text = forecast.degress
    }
}