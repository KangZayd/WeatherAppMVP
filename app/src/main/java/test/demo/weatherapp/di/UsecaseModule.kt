package test.demo.weatherapp.di

import dagger.Module
import dagger.Provides
import test.demo.weatherapp.data.NetworkHandler
import test.demo.weatherapp.data.WeatherApiService
import test.demo.weatherapp.data.usecases.CurrentWeatherUsecase
import test.demo.weatherapp.data.usecases.FetchWeatherInfoUsecase
import test.demo.weatherapp.data.usecases.WeatherForecasteUsecase
import javax.inject.Named

@Module(includes = arrayOf(ApiServiceModule::class))
class UsecaseModule {

    @Provides
    fun getFetchWeatherInfoUsecase(@Named("apiservice")apiServide : WeatherApiService) : FetchWeatherInfoUsecase {
        return FetchWeatherInfoUsecase(getWeatherForecasteUsecase(apiServide),getCurrentWeatherUsecase(apiServide))
    }

    @Provides
    fun getWeatherForecasteUsecase(apiServide : WeatherApiService) : WeatherForecasteUsecase {
        return WeatherForecasteUsecase(apiServide)
    }

    @Provides
    fun getCurrentWeatherUsecase(apiServide : WeatherApiService) : CurrentWeatherUsecase {
        return CurrentWeatherUsecase(apiServide)
    }

}