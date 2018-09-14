package test.demo.weatherapp.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherInfo(var currentWeatherResponse: CurrentWeatherResponse,var list : List<Forecastday>) : Parcelable