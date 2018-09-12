package test.gojek.gojektest


import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import test.gojek.gojektest.ui.weather_info.activities.MainActivity
import android.app.Activity
import org.robolectric.android.controller.ActivityController
import test.gojek.gojektest.ui.weather_info.presenter.MainPresenter
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.junit.Rule




@RunWith(RobolectricTestRunner::class)
class FetchCityTest {

    lateinit var controller: ActivityController<MainActivity>
    @get:Rule
    val rule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        controller = Robolectric.buildActivity(MainActivity::class.java)
    }


    @Test
    fun onCreateTest() {

        controller.create()
    }

}