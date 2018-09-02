package test.gojek.gojektest

import android.app.Application
import test.gojek.gojektest.data.NetworkHandler

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        NetworkHandler.init()
    }
}