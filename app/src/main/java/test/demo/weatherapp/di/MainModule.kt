package test.demo.weatherapp.di

import dagger.Module
import dagger.Provides
import test.demo.weatherapp.util.SchedulersUtil

@Module
class MainModule {

    @Provides fun getSchedulers() : SchedulersUtil{
        return SchedulersUtil()
    }
}