package test.demo.weatherapp.data.usecases

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import test.demo.weatherapp.data.NetworkHandler
import test.demo.weatherapp.data.response.CurrentWeatherResponse
import test.demo.weatherapp.data.response.ForecastWeatherResponse
import test.demo.weatherapp.data.response.WeatherInfo
import test.demo.weatherapp.util.isToday
import javax.inject.Inject

open class FetchWeatherInfoUsecase @Inject constructor(var forecasteUsecase: WeatherForecasteUsecase, var currentWeatherUsecase: CurrentWeatherUsecase) : Interactor<WeatherInfo> {

    lateinit var cityName: String

    override fun execute(): Flowable<WeatherInfo> {

        forecasteUsecase.cityName = cityName
        currentWeatherUsecase.cityName = cityName

        var flowable = currentWeatherUsecase.execute().zipWith(forecasteUsecase.execute(),
                object : BiFunction<CurrentWeatherResponse, ForecastWeatherResponse, WeatherInfo> {
                    override fun apply(t1: CurrentWeatherResponse, t2: ForecastWeatherResponse): WeatherInfo {
                        return WeatherInfo(t1, t2.forecast.forecastday.filter { !isToday(it.date) })
                    }
                })


        return flowable
    }


}