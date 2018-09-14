package test.demo.weatherapp.ui.weather_info.activities

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.pm.PackageManager
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import test.demo.weatherapp.R
import test.demo.weatherapp.data.response.WeatherInfo
import test.demo.weatherapp.di.DaggerMainComponent
import test.demo.weatherapp.di.MainComponent
import test.demo.weatherapp.location.FetchCurrentCity
import test.demo.weatherapp.ui.base.BaseActivity
import test.demo.weatherapp.ui.base.Response
import test.demo.weatherapp.ui.weather_info.fragment.ErrorFragment
import test.demo.weatherapp.ui.weather_info.fragment.WeatherForecastFragment
import test.demo.weatherapp.ui.weather_info.presenter.MainPresenter
import test.demo.weatherapp.util.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), ErrorFragment.OnRetryListener {

    lateinit var mainComponent: MainComponent
    @Inject
    lateinit var fetchCurrentCity: FetchCurrentCity
    val COARSE_LOCATION = 1;

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun init() {
        mainComponent = DaggerMainComponent.create();
        mainComponent.inject(this)
        mainComponent.inject(presenter)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addStatusBarColor()
        showLoading(true)
        presenter.observeForWeatherInfo().observe(this@MainActivity, Observer { response -> response?.let { processResponse(response) } })
        checkForPermissions()
    }

    fun showLoading(showLoading: Boolean) {
        if (showLoading) {
//            startLoading()
            initAnimations()
        } else {
            imvLoading.clearAnimation()
            imvLoading.visibility = View.GONE
        }
    }

    fun processResponse(response: Response) {

        when (response) {

            is Response.OnLoading -> {
                showLoading(response.showLoading)
            }
            is Response.SuccessResponse -> {
                var fragment = WeatherForecastFragment()
                var bundle = Bundle()
                bundle.putParcelable("weather_info", response.s as WeatherInfo)
                fragment.arguments = bundle
                addWeatherScreenAnimation(fragment)
                supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment, "success").commit()
            }
            is Response.ErrorResponse -> {
                showError(response.s)
            }
        }
    }

    fun showError(errorString: String?) {
        var fragment = ErrorFragment()
        addErrorAnimation(fragment)
        var bundle = Bundle()
        bundle.putString("error_msg", errorString)
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment, "error").commit()
    }

    override fun onRetryClick() {

        addStatusBarColor()

        for (fragment in supportFragmentManager.fragments) {
            fragment?.let {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }
        fetchWeatherInfo()
    }

    fun addStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window?.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark))
        }
    }

    @SuppressLint("MissingPermission")
    fun fetchWeatherInfo() {
        fetchCurrentCity.observe(this@MainActivity, Observer {
            it?.let {
                processCityResponse(it)
            }
        })
    }

    fun processCityResponse(response: Response) {

        when (response) {
            is Response.ErrorResponse -> {
                showError(response.s)
            }
            is Response.SuccessResponse -> {
                presenter.loadData(response.s as String)
            }
        }

    }

    fun startLoading() {
        imvLoading.visibility = View.VISIBLE
        imvLoading.startAnimation(getRotateAnimation())
    }

    fun checkForPermissions() {

        if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), COARSE_LOCATION)
        } else {
            fetchWeatherInfo()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            COARSE_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    fetchWeatherInfo()
                } else {
                    Toast.makeText(this@MainActivity, "Need permission to fetch weather information", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    fun initAnimations() {
        var drawable = imvLoading.drawable as AnimationDrawable
        drawable.start()
    }


}
