package test.demo.weatherapp.data.usecases

import io.reactivex.Flowable
import test.demo.weatherapp.data.WeatherApiService
import test.demo.weatherapp.data.response.ForecastWeatherResponse

open class WeatherForecasteUsecase(var apiService: WeatherApiService) : Interactor<ForecastWeatherResponse> {
    lateinit var cityName : String
    override fun execute(): Flowable<ForecastWeatherResponse> {
        return apiService.getWeatherForecast(cityName = cityName)
    }
}