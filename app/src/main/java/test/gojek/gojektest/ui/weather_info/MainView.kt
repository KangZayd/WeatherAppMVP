package test.gojek.gojektest.ui.weather_info

import test.gojek.gojektest.ui.base.BaseView
import test.gojek.gojektest.ui.base.Response

interface MainView : BaseView {

    fun showLoading()
    fun hideLoading()
    fun onLoad(response : Response)
}