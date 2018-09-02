package test.gojek.gojektest.data

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import test.gojek.gojektest.data.response.CurrentWeatherResponse
import test.gojek.gojektest.data.response.ForecastWeatherResponse

interface WeatherApiService {

    @GET("/v1/current.json")
    fun getCurrentWeather(@Query("key") key : String = "c703792f9a4540e595650713180109", @Query("q") cityName: String = "mumbai"): Flowable<CurrentWeatherResponse>

    @GET("/v1/forecast.json")
    fun getWeatherForecast(@Query("key") key : String = "c703792f9a4540e595650713180109",@Query("q") cityName: String = "mumbai",@Query("days") day : String = "5"): Flowable<ForecastWeatherResponse>
}