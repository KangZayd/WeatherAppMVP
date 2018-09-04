package test.gojek.gojektest.di

import dagger.Module
import dagger.Provides
import test.gojek.gojektest.data.NetworkHandler
import test.gojek.gojektest.data.WeatherApiService
import test.gojek.gojektest.data.usecases.CurrentWeatherUsecase
import test.gojek.gojektest.data.usecases.FetchWeatherInfoUsecase
import test.gojek.gojektest.data.usecases.WeatherForecasteUsecase
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