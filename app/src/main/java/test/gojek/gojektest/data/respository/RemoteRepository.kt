package test.gojek.gojektest.data.respository

import io.reactivex.Flowable
import test.gojek.gojektest.data.WeatherApiService
import test.gojek.gojektest.data.response.CurrentWeatherResponse
import test.gojek.gojektest.data.response.ForecastWeatherResponse

class RemoteRepository constructor(var apiService: WeatherApiService) {

    fun getCurrentWeather(): Flowable<CurrentWeatherResponse> {
        return apiService.getCurrentWeather()
    }

    fun getWeatherforecast(): Flowable<ForecastWeatherResponse> {
        return apiService.getWeatherForecast()
    }
}