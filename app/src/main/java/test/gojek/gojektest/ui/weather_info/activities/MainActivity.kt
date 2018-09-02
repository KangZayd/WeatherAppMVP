package test.gojek.gojektest.ui.weather_info.activities


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import test.gojek.gojektest.R
import test.gojek.gojektest.data.response.WeatherInfo
import test.gojek.gojektest.ui.base.BaseActivity
import test.gojek.gojektest.ui.base.Response
import test.gojek.gojektest.ui.weather_info.fragment.ErrorFragment
import test.gojek.gojektest.ui.weather_info.presenter.MainPresenter
import test.gojek.gojektest.ui.weather_info.fragment.WeatherForecastFragment
import test.gojek.gojektest.util.addErrorAnimation
import test.gojek.gojektest.util.addWeatherScreenAnimation
import test.gojek.gojektest.util.getRotateAnimation


class MainActivity : BaseActivity<MainPresenter>(), ErrorFragment.OnRetryListener {


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.observeForWeatherInfo().observe(this@MainActivity, Observer { response -> response?.let { processResponse(response) } })
        fetchWeatherInfo()
    }

    fun processResponse(response: Response) {

        when (response) {

            is Response.OnLoading -> {
                if (response.showLoading) {
                    startLoading()
                } else {
                    imvLoading.clearAnimation()
                    imvLoading.visibility = View.GONE
                }
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
                var fragment = ErrorFragment()
                addErrorAnimation(fragment)
                supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment, "error").commit()

            }

        }

    }

    override fun onRetryClick() {
        for (fragment in supportFragmentManager.fragments) {
            fragment?.let {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }

        fetchWeatherInfo()
    }

    fun fetchWeatherInfo() {
        presenter.loadData()
    }

    fun startLoading() {
        imvLoading.visibility = View.VISIBLE
        imvLoading.startAnimation(getRotateAnimation())
    }
}
