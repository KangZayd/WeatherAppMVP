package test.gojek.gojektest.data.usecases

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import test.gojek.gojektest.data.NetworkHandler
import test.gojek.gojektest.data.response.CurrentWeatherResponse
import test.gojek.gojektest.data.response.ForecastWeatherResponse
import test.gojek.gojektest.data.response.WeatherInfo
import test.gojek.gojektest.util.isToday
import javax.inject.Inject

class FetchWeatherInfoUsecase @Inject constructor(var forecasteUsecase: WeatherForecasteUsecase,var currentWeatherUsecase: CurrentWeatherUsecase) : Interactor<WeatherInfo> {

    override fun execute(): Flowable<WeatherInfo> {

//        var currentWeatherUsecase = CurrentWeatherUsecase(apiService)
//        var forecasteUsecase = WeatherForecasteUsecase(apiService)

        var flowable = currentWeatherUsecase.execute().zipWith(forecasteUsecase.execute(),
                object : BiFunction<CurrentWeatherResponse, ForecastWeatherResponse, WeatherInfo> {
                    override fun apply(t1: CurrentWeatherResponse, t2: ForecastWeatherResponse): WeatherInfo {
                        return WeatherInfo(t1, t2.forecast.forecastday.filter { !isToday(it.date) })
                    }
                })


        return flowable
    }


}