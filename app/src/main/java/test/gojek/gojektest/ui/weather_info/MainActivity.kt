package test.gojek.gojektest.ui.weather_info


import android.os.Bundle
import test.gojek.gojektest.R
import test.gojek.gojektest.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
//c703792f9a4540e595650713180109
//https://api.apixu.com/v1/current.json?key=c703792f9a4540e595650713180109&q=Paris
//http://api.apixu.com/v1/forecast.json?key=c703792f9a4540e595650713180109&q=Paris