package test.demo.weatherapp.di

import dagger.Module
import dagger.Provides
import test.demo.weatherapp.data.NetworkHandler
import test.demo.weatherapp.data.WeatherApiService
import javax.inject.Named

@Module
class ApiServiceModule {

    @Provides @Named("apiservice")fun getApiService() : WeatherApiService{
        return NetworkHandler.getApiService()
    }
}