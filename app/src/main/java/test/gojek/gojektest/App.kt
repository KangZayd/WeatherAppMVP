package test.gojek.gojektest

import android.app.Application
import android.content.Context
import test.gojek.gojektest.data.NetworkHandler

lateinit var appContext : Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        NetworkHandler.init()
    }



}