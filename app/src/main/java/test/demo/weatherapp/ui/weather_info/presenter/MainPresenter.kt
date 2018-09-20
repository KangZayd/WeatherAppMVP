package test.demo.weatherapp.ui.weather_info.presenter

import android.arch.lifecycle.MutableLiveData
import io.reactivex.subscribers.ResourceSubscriber
import test.demo.weatherapp.data.response.WeatherInfo
import test.demo.weatherapp.data.usecases.FetchWeatherInfoUsecase
import test.demo.weatherapp.ui.base.BasePresenter
import test.demo.weatherapp.ui.base.Response
import test.demo.weatherapp.util.SchedulersUtil
import javax.inject.Inject

open class MainPresenter @Inject constructor() : BasePresenter() {

    var livedata = MutableLiveData<Response>()

    @Inject
    lateinit var fetchWeatherInfoUsecase: FetchWeatherInfoUsecase

    @Inject
    lateinit var schedulers: SchedulersUtil

    fun observeForWeatherInfo(): MutableLiveData<Response> {
        return livedata
    }


    fun loadData(name: String) {
        fetchWeatherInfoUsecase.cityName = name

        disposables.add(fetchWeatherInfoUsecase.execute().subscribeOn(schedulers.io()).observeOn(schedulers.ui())
                .subscribeWith(object : ResourceSubscriber<WeatherInfo>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: WeatherInfo) {
                        livedata.value = Response.SuccessResponse(t)
                        System.out.println("the data is " + t.toString())
                    }

                    override fun onError(t: Throwable?) {

                        var message: String?

                        if (t is retrofit2.adapter.rxjava2.HttpException && t.code() == 401) {
                            message = "Get you api key from https://www.apixu.com/"
                        } else {
                            message = t?.localizedMessage
                        }

                        livedata.value = Response.ErrorResponse(message)
                    }
                }))
    }


}