package test.gojek.gojektest.data.usecases

import io.reactivex.Flowable
import test.gojek.gojektest.data.WeatherApiService
import test.gojek.gojektest.data.response.CurrentWeatherResponse


class CurrentWeatherUsecase(var apiService: WeatherApiService) : Interactor<CurrentWeatherResponse> {

    override fun execute(): Flowable<CurrentWeatherResponse> {
        return apiService.getCurrentWeather()
    }
}
