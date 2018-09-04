package test.gojek.gojektest.di

import dagger.Module
import dagger.Provides
import test.gojek.gojektest.data.NetworkHandler
import test.gojek.gojektest.data.WeatherApiService
import javax.inject.Named

@Module
class ApiServiceModule {

    @Provides @Named("apiservice")fun getApiService() : WeatherApiService{
        return NetworkHandler.getApiService()
    }
}