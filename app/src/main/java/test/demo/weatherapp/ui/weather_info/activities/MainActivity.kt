package test.demo.weatherapp.ui.weather_info.activities

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.pm.PackageManager
import android.graphics.drawable.AnimationDrawable
import android.location.Location
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
import test.demo.weatherapp.location.LocationListener
import test.demo.weatherapp.location.LocationSettingsHandler
import test.demo.weatherapp.location.REQUEST_CHECK_SETTINGS
import test.demo.weatherapp.ui.base.BaseActivity
import test.demo.weatherapp.ui.base.LocationResponse
import test.demo.weatherapp.ui.base.Response
import test.demo.weatherapp.ui.weather_info.fragment.ErrorFragment
import test.demo.weatherapp.ui.weather_info.fragment.WeatherForecastFragment
import test.demo.weatherapp.ui.weather_info.presenter.MainPresenter
import test.demo.weatherapp.util.addErrorAnimation
import test.demo.weatherapp.util.addWeatherScreenAnimation
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), ErrorFragment.OnRetryListener {

    lateinit var mainComponent: MainComponent
    @Inject
    lateinit var fetchCurrentCity: FetchCurrentCity
    @Inject
    lateinit var locationListener: LocationListener
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
        fetchUserCity()
    }

    fun showLoading(showLoading: Boolean) {
        if (showLoading) {
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
            is Response.LocationResponse -> {
                requestUserLocation()
            }
        }
    }

    fun showError(errorString: String?) {
        var fragment = ErrorFragment()
        addErrorAnimation(fragment)
        var bundle = Bundle()
        bundle.putString("error_msg", errorString)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment, "error").commit()
    }

    override fun onRetryClick() {

        addStatusBarColor()

        for (fragment in supportFragmentManager.fragments) {
            fragment?.let {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }
        fetchUserCity()
    }

    fun addStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window?.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark))
        }
    }

    /**
     * listen to user location updates
     */
    @SuppressLint("MissingPermission")
    fun requestUserLocation() {

        locationListener.observe(this@MainActivity, locationObserver)

    }

    var locationObserver = Observer<LocationResponse> { response -> response?.let { processLocationResponse(response) } }

    fun processLocationResponse(locationResponse: LocationResponse) {

        when (locationResponse) {

            is LocationResponse.LocationSettings -> {
                /**
                 * check if we have appropriate settings
                 */

                var locationSettingsHandler = LocationSettingsHandler(this@MainActivity)
                locationSettingsHandler.checkLocationSettings { }
            }
            is LocationResponse.FetchLocation -> {

                /**
                 * remove observer
                 */
                locationListener.removeObserver(locationObserver)
                fetchCurrentCity(locationResponse.location)
            }
        }
    }

    /**
     * Fetch the user city
     */
    fun fetchCurrentCity(location: Location) {
        fetchCurrentCity.location = location
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

    /**
     * Open permissions dialog to ask the location permission
     */
    fun checkForPermissions() {

        if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), COARSE_LOCATION)
        } else {
            requestUserLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            COARSE_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    requestUserLocation()
                } else {
//                    Toast.makeText(this@MainActivity, "Need permission to fetch weather information", Toast.LENGTH_SHORT).show()
                    showError("Need permission to fetch weather information")
                }
            }
            REQUEST_CHECK_SETTINGS -> {
                requestUserLocation()
            }
        }
    }

    fun initAnimations() {
        var drawable = imvLoading.drawable as AnimationDrawable
        drawable.start()
    }

    /**
     * fetch the user city
     */

    fun fetchUserCity(){
        /**
         * check if we have the permissiion to acces the user location
         */
        checkForPermissions()
    }
}
