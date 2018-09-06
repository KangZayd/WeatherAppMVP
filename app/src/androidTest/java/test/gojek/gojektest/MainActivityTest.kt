package test.gojek.gojektest

import android.support.test.runner.AndroidJUnit4
import test.gojek.gojektest.ui.weather_info.activities.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith
import android.app.Activity
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.filters.SmallTest
import org.junit.Assert
import org.junit.Test
import test.gojek.gojektest.ui.base.Response


@RunWith(AndroidJUnit4::class)
@SmallTest
class MainActivityTest {

    @get:Rule
    val mainActivityActivityTestRule = MainActivityTestRule(MainActivity::class.java)

    @Test
    fun testUi() {
        val activity = mainActivityActivityTestRule.activity
        activity.processResponse(Response.ErrorResponse(""))
        Espresso.onView(ViewMatchers.withId(R.id.btnRetry)).perform(click())

    }

}