//package test.gojek.gojektest
//
//import io.reactivex.Flowable
//import io.reactivex.android.plugins.RxAndroidPlugins
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.schedulers.Schedulers
//import org.junit.*
//import org.junit.runner.RunWith
//import org.mockito.InjectMocks
//import org.mockito.Mock
//import org.mockito.Mockito
//import test.gojek.gojektest.data.response.CurrentWeatherResponse
//import test.gojek.gojektest.data.response.ForecastWeatherResponse
//import test.gojek.gojektest.data.response.WeatherInfo
//import test.gojek.gojektest.data.usecases.CurrentWeatherUsecase
//import test.gojek.gojektest.data.usecases.FetchWeatherInfoUsecase
//import test.gojek.gojektest.data.usecases.WeatherForecasteUsecase
//import test.gojek.gojektest.di.MainComponent
//import test.gojek.gojektest.ui.weather_info.presenter.MainPresenter
//import test.gojek.gojektest.util.SchedulersUtil
//import java.io.ByteArrayOutputStream
//import java.util.concurrent.TimeUnit
//import org.junit.Before
//import org.mockito.Mockito.*
//import org.mockito.Spy
//import java.io.PrintStream
//
//
//@RunWith(PowerMockRunner::class)
//@PrepareForTest(SchedulersUtil::class)
//class PresenterTest {
//
//    @ClassRule
//    var schedulers = RxImmediateSchedulerRule()
//
//    @get:Rule
//    var rule = DaggerMock.rule<MainComponent>()
//
//
//    lateinit var schedulersUtil: SchedulersUtil
//    @Mock
//    lateinit var weatherForecasteUsecase: WeatherForecasteUsecase
//    @Mock
//    lateinit var currentWeatherUsecase: CurrentWeatherUsecase
//    @Spy
//    @InjectMocks
//    lateinit var fetchWeatherInfoUsecase: FetchWeatherInfoUsecase
//    @InjectMocks
//    lateinit var presenter: MainPresenter
//
//    companion object {
//
//        @BeforeClass
//        @JvmStatic
//        fun setUpRxSchedulers() {
//            RxAndroidPlugins.setInitMainThreadSchedulerHandler({ scheduler -> Schedulers.trampoline() })
//        }
//    }
//
//
//    @Test
//    fun checkLoadForecast() {
//
//        initFetchWeatherInfoUseCase()
//        presenter.loadData("mumbai")
//    }
//
//    @Test
//    fun checkValues() {
//        initFetchWeatherInfoUseCase()
//        fetchWeatherInfoUsecase.cityName = "mumbai"
//        fetchWeatherInfoUsecase.execute().test().assertOf {
//            Assert.assertNotNull(it.values())
//        }
//    }
//
//    @Test
//    fun verifyCall() {
//        initFetchWeatherInfoUseCase()
//        presenter.loadData("mumbai")
//
//        verify(fetchWeatherInfoUsecase).execute()
//    }
//
//    fun initFetchWeatherInfoUseCase() {
//        presenter.fetchWeatherInfoUsecase = fetchWeatherInfoUsecase
//        schedulersUtil = mock(SchedulersUtil::class.java)
//        presenter.schedulers = schedulersUtil
//        Mockito.`when`(weatherForecasteUsecase.execute()).thenReturn(Flowable.just(mock(ForecastWeatherResponse::class.java, RETURNS_DEEP_STUBS)).delay(1, TimeUnit.SECONDS))
//        Mockito.`when`(currentWeatherUsecase.execute()).thenReturn(Flowable.just(mock(CurrentWeatherResponse::class.java, RETURNS_DEEP_STUBS)).delay(2, TimeUnit.SECONDS))
//        Mockito.`when`(presenter.schedulers.io()).thenReturn(Schedulers.io())
//        Mockito.`when`(presenter.schedulers.ui()).thenReturn(AndroidSchedulers.mainThread())
//    }
//}