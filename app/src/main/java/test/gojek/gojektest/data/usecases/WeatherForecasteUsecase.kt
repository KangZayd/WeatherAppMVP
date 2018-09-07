package test.gojek.gojektest.data.usecases

import io.reactivex.Flowable
import test.gojek.gojektest.data.WeatherApiService
import test.gojek.gojektest.data.response.ForecastWeatherResponse

class WeatherForecasteUsecase(var apiService: WeatherApiService) : Interactor<ForecastWeatherResponse> {
    lateinit var cityName : String
    override fun execute(): Flowable<ForecastWeatherResponse> {
        return apiService.getWeatherForecast(cityName = cityName)
    }
}