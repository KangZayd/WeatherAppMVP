package test.demo.weatherapp.di

import dagger.Component
import test.demo.weatherapp.ui.weather_info.activities.MainActivity
import test.demo.weatherapp.ui.weather_info.presenter.MainPresenter
import test.demo.weatherapp.util.SchedulersUtil

@Component (modules = arrayOf(MainModule::class,UsecaseModule::class))
interface MainComponent {

    fun inject(presenter : MainPresenter)
    fun inject(activity : MainActivity)
}