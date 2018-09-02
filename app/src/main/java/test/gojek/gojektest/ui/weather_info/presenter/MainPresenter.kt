package test.gojek.gojektest.ui.weather_info.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.subscribers.ResourceSubscriber
import test.gojek.gojektest.data.response.WeatherInfo
import test.gojek.gojektest.data.usecases.FetchWeatherInfoUsecase
import test.gojek.gojektest.ui.base.BasePresenter
import test.gojek.gojektest.ui.base.Response
import test.gojek.gojektest.util.SchedulersUtil

class MainPresenter : BasePresenter() {

    var livedata = MutableLiveData<Response>()
    var schedulers = SchedulersUtil()

    fun observeForWeatherInfo(): MutableLiveData<Response> {
        return livedata
    }


    fun loadData() {

        var fetchWeatherInfoUsecase = FetchWeatherInfoUsecase()

        disposables.add(fetchWeatherInfoUsecase.execute().subscribeOn(schedulers.io()).observeOn(schedulers.ui())
                .doOnSubscribe {
                    livedata.value = Response.OnLoading(true)
                }
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