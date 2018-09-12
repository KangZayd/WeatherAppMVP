package test.gojek.gojektest

import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.RETURNS_DEEP_STUBS
import org.mockito.Mockito.mock
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import test.gojek.gojektest.data.response.CurrentWeatherResponse
import test.gojek.gojektest.data.response.ForecastWeatherResponse
import test.gojek.gojektest.data.response.WeatherInfo
import test.gojek.gojektest.data.usecases.CurrentWeatherUsecase
import test.gojek.gojektest.data.usecases.FetchWeatherInfoUsecase
import test.gojek.gojektest.data.usecases.WeatherForecasteUsecase
import test.gojek.gojektest.di.MainComponent
import test.gojek.gojektest.ui.weather_info.presenter.MainPresenter
import test.gojek.gojektest.util.SchedulersUtil
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit
import org.junit.Before
import java.io.PrintStream


@RunWith(PowerMockRunner::class)
@PrepareForTest(SchedulersUtil::class)
class PresenterTest {

    @ClassRule
    var schedulers = RxImmediateSchedulerRule()

    @get:Rule
    var rule = DaggerMock.rule<MainComponent>()


    lateinit var schedulersUtil: SchedulersUtil
    @Mock
    lateinit var weatherForecasteUsecase: WeatherForecasteUsecase
    @Mock
    lateinit var currentWeatherUsecase: CurrentWeatherUsecase
    @InjectMocks
    lateinit var fetchWeatherInfoUsecase: FetchWeatherInfoUsecase
    @InjectMocks
    lateinit var presenter: MainPresenter

    companion object {

        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler({ scheduler -> Schedulers.trampoline() })
        }
    }


    @Test
    fun checkLoadForecast() {
        presenter.fetchWeatherInfoUsecase = fetchWeatherInfoUsecase
        initFetchWeatherInfoUseCase()
        presenter.loadData("mumbai")
    }

    @Test
    fun checkValues() {
        initFetchWeatherInfoUseCase()
        fetchWeatherInfoUsecase.cityName = "mumbai"
        fetchWeatherInfoUsecase.execute().test().assertOf {
            Assert.assertNotNull(it.values())
        }
    }

    fun initFetchWeatherInfoUseCase() {
        schedulersUtil = mock(SchedulersUtil::class.java)
        presenter.schedulers = schedulersUtil
        Mockito.`when`(weatherForecasteUsecase.execute()).thenReturn(Flowable.just(mock(ForecastWeatherResponse::class.java, RETURNS_DEEP_STUBS)).delay(1, TimeUnit.SECONDS))
        Mockito.`when`(currentWeatherUsecase.execute()).thenReturn(Flowable.just(mock(CurrentWeatherResponse::class.java, RETURNS_DEEP_STUBS)).delay(2, TimeUnit.SECONDS))
        Mockito.`when`(presenter.schedulers.io()).thenReturn(Schedulers.io())
        Mockito.`when`(presenter.schedulers.ui()).thenReturn(AndroidSchedulers.mainThread())
    }
}