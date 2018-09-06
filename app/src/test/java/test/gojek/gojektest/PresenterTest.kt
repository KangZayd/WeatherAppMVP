package test.gojek.gojektest

import android.arch.lifecycle.MutableLiveData
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import test.gojek.gojektest.ui.base.Response
import test.gojek.gojektest.ui.weather_info.presenter.MainPresenter

class PresenterTest {

    @Mock
    var livedata = MutableLiveData<Response>()
    @Mock
    lateinit var presenter : MainPresenter

    @Before
    fun setUp(){

        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter()
    }

    @Test
    fun checkLoadForecast(){

    }

}