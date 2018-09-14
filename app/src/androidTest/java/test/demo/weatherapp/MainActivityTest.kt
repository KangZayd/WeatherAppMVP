package test.demo.weatherapp

import android.support.test.runner.AndroidJUnit4
import test.demo.weatherapp.ui.weather_info.activities.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith
import android.app.Activity
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.filters.SmallTest
import android.support.v7.widget.RecyclerView
import org.junit.Assert
import org.junit.Test
import test.demo.weatherapp.ui.base.Response
import test.demo.weatherapp.ui.weather_info.fragment.WeatherForecastFragment
import android.support.test.espresso.contrib.RecyclerViewActions
import test.demo.weatherapp.ui.weather_info.adapter.WeatherForecastHolder

@RunWith(AndroidJUnit4::class)
@SmallTest
class MainActivityTest {

    @get:Rule
    val mainActivityActivityTestRule = MainActivityTestRule(MainActivity::class.java)

//    @Test
//    fun testWeatherFragment(){
//
//        var fragment = mainActivityActivityTestRule.activity.supportFragmentManager.findFragmentById(R.id.flContainer)
//
//        when(fragment){
//            is WeatherForecastFragment -> {
//                Espresso.onView(ViewMatchers.withId(R.id.rvForecastList))
//                        .perform(RecyclerViewActions.actionOnItemAtPosition<WeatherForecastHolder>(1, click()))
//            }
//        }
//
//    }

    @Test
    fun testUi() {
        val activity = mainActivityActivityTestRule.activity
        activity.processResponse(Response.ErrorResponse(""))
        Espresso.onView(ViewMatchers.withId(R.id.btnRetry)).perform(click())

    }

}