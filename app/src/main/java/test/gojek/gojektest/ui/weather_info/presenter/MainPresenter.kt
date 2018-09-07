package test.gojek.gojektest.ui.weather_info.presenter

import android.arch.lifecycle.MutableLiveData
import io.reactivex.subscribers.ResourceSubscriber
import test.gojek.gojektest.data.response.WeatherInfo
import test.gojek.gojektest.data.usecases.FetchWeatherInfoUsecase
import test.gojek.gojektest.ui.base.BasePresenter
import test.gojek.gojektest.ui.base.Response
import test.gojek.gojektest.util.SchedulersUtil
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter() {

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
//                .doOnSubscribe {
//                    livedata.value = Response.OnLoading(true)
//                }
                .subscribeWith(object : ResourceSubscriber<WeatherInfo>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: WeatherInfo) {
                        livedata.value = Response.SuccessResponse(t)
                    }

                    override fun onError(t: Throwable?) {
                        livedata.value = Response.ErrorResponse(t?.localizedMessage)
                    }

                }))

    }


}