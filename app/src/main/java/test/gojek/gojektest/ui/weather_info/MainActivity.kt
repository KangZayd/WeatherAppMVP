package test.gojek.gojektest.ui.weather_info


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import test.gojek.gojektest.R
import test.gojek.gojektest.ui.base.BaseActivity
import test.gojek.gojektest.ui.base.Response

class MainActivity : BaseActivity<MainView, MainPresenter>() {
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
        presenter.loadData()
    }

    fun processResponse(response: Response) {

        when (response) {

            is Response.OnLoading -> if (response.showLoading) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
            is Response.SuccessResponse -> supportFragmentManager.beginTransaction().replace(R.id.flContainer, WeatherForecastFragment(), "success").commit()
            is Response.ErrorResponse -> supportFragmentManager.beginTransaction().replace(R.id.flContainer, ErrorFragment(), "error").commit()

        }

    }

}
