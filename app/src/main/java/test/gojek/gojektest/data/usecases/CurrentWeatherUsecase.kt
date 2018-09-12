package test.gojek.gojektest.data.usecases

import io.reactivex.Flowable
import test.gojek.gojektest.data.WeatherApiService
import test.gojek.gojektest.data.response.CurrentWeatherResponse


open class CurrentWeatherUsecase(var apiService: WeatherApiService) : Interactor<CurrentWeatherResponse> {
    lateinit var cityName : String
    override fun execute(): Flowable<CurrentWeatherResponse> {
        return apiService.getCurrentWeather(cityName = cityName)
    }
}
