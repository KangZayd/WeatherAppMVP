package test.demo.weatherapp

import android.app.Application
import android.content.Context
import test.demo.weatherapp.data.NetworkHandler

lateinit var appContext : Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        NetworkHandler.init()
    }



}