package test.gojek.gojektest.di

import dagger.Module
import dagger.Provides
import test.gojek.gojektest.util.SchedulersUtil

@Module
class MainModule {

    @Provides fun getSchedulers() : SchedulersUtil{
        return SchedulersUtil()
    }
}