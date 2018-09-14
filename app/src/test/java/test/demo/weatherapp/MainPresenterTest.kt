package test.demo.weatherapp

import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import test.demo.weatherapp.data.response.Current
import test.demo.weatherapp.data.response.Location
import test.demo.weatherapp.data.response.WeatherInfo
import test.demo.weatherapp.data.usecases.CurrentWeatherUsecase
import test.demo.weatherapp.data.usecases.FetchWeatherInfoUsecase
import test.demo.weatherapp.data.usecases.WeatherForecasteUsecase
import test.demo.weatherapp.ui.weather_info.presenter.MainPresenter
import test.demo.weatherapp.util.SchedulersUtil
import java.util.concurrent.TimeUnit

class MainPresenterTest {

    val city = "mumbai"
    @get:Rule
    var mockitoRule = MockitoJUnit.rule()
//    @get:Rule
//    var rule = DaggerMock.rule<MainComponent>()

    lateinit var schedulersUtil: SchedulersUtil
    @Mock
    lateinit var weatherForecasteUsecase: WeatherForecasteUsecase
    @Mock
    lateinit var currentWeatherUsecase: CurrentWeatherUsecase
    //    @Spy
//    @InjectMocks
    lateinit var fetchWeatherInfoUsecase: FetchWeatherInfoUsecase
    @InjectMocks
    lateinit var presenter: MainPresenter

    companion object {
        //
        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler({ scheduler -> Schedulers.trampoline() })
        }
    }

    @Before
    fun init() {
        fetchWeatherInfoUsecase = mock(FetchWeatherInfoUsecase::class.java)
        schedulersUtil = mock(SchedulersUtil::class.java)
        presenter.schedulers = schedulersUtil
        presenter.fetchWeatherInfoUsecase = fetchWeatherInfoUsecase
//        Mockito.`when`(weatherForecasteUsecase.execute()).thenReturn(Flowable.just(mock(ForecastWeatherResponse::class.java, RETURNS_DEEP_STUBS)).delay(1, TimeUnit.SECONDS))
//        Mockito.`when`(currentWeatherUsecase.execute()).thenReturn(Flowable.just(mock(CurrentWeatherResponse::class.java, RETURNS_DEEP_STUBS)).delay(2, TimeUnit.SECONDS))
//        Mockito.`when`(fetchWeatherInfoUsecase.execute()).thenReturn(Flowable.just(getWeatherInfoTestData()).delay(2, TimeUnit.SECONDS))
        Mockito.`when`(presenter.schedulers.io()).thenReturn(Schedulers.io())
        Mockito.`when`(presenter.schedulers.ui()).thenReturn(AndroidSchedulers.mainThread())
    }

//    fun getWeatherInfoTestData(): WeatherInfo {
//        var weatherInfo = mock(WeatherInfo::class.java, RETURNS_DEEP_STUBS)
////        var location = mock(Location::class.java)
//        var location = Location(name = "mumbai")
//        location.name = "mumbai"
//        var current = mock(Current::class.java)
//        current.temp_c = 27.4
//
//        weatherInfo.currentWeatherResponse.location = location
//        weatherInfo.currentWeatherResponse.current = current
//
//        return weatherInfo
//    }

    @Test
    fun testLoad() {
        Assert.assertNotNull(presenter.fetchWeatherInfoUsecase)
        Assert.assertNotNull(weatherForecasteUsecase)
        Assert.assertNotNull(currentWeatherUsecase)
        Assert.assertNotNull(presenter.disposables)

    }

//    @Test
//    fun testLoadData() {
//
//        presenter.loadData(city)
//        verify(fetchWeatherInfoUsecase).cityName = ArgumentMatchers.anyString()
//        verify(fetchWeatherInfoUsecase).execute()
//    }

//    @Test
//    fun captureTestArguments() {
//
//        presenter.loadData(city)
//        verify(presenter.livedata).value
//    }
}