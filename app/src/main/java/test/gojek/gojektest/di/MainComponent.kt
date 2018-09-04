package test.gojek.gojektest.di

import dagger.Component
import test.gojek.gojektest.ui.weather_info.activities.MainActivity
import test.gojek.gojektest.ui.weather_info.presenter.MainPresenter
import test.gojek.gojektest.util.SchedulersUtil

@Component (modules = arrayOf(MainModule::class,UsecaseModule::class))
interface MainComponent {

    fun inject(presenter : MainPresenter)
    fun inject(activity : MainActivity)
}