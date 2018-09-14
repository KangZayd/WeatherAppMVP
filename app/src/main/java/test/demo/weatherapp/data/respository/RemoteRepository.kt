package test.demo.weatherapp.data.respository

import io.reactivex.Flowable
import test.demo.weatherapp.data.WeatherApiService
import test.demo.weatherapp.data.response.CurrentWeatherResponse
import test.demo.weatherapp.data.response.ForecastWeatherResponse

class RemoteRepository constructor(var apiService: WeatherApiService) {

    fun getCurrentWeather(): Flowable<CurrentWeatherResponse> {
        return apiService.getCurrentWeather()
    }

    fun getWeatherforecast(): Flowable<ForecastWeatherResponse> {
        return apiService.getWeatherForecast()
    }
}