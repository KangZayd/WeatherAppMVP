package test.gojek.gojektest.data.usecases

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import test.gojek.gojektest.data.NetworkHandler
import test.gojek.gojektest.data.response.ForecastWeatherResponse
import test.gojek.gojektest.data.response.WeatherInfo

class FetchWeatherInfoUsecase : Interactor<WeatherInfo> {
    override fun execute(): Flowable<WeatherInfo> {
        var apiService = NetworkHandler.getApiService();
        var currentWeatherUsecase = CurrentWeatherUsecase(apiService)
        var forecasteUsecase = WeatherForecasteUsecase(apiService)

        return currentWeatherUsecase.execute()
                .zipWith(forecasteUsecase.execute(), BiFunction { t1, t2 ->
                    WeatherInfo(t1, t2.forecast.forecastday)
                }, false)

    }


}