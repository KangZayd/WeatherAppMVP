package test.gojek.gojektest

import android.support.test.filters.SmallTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import test.gojek.gojektest.ui.weather_info.activities.MainActivity


@RunWith(RobolectricTestRunner::class)
@SmallTest
class FetchCityTest {

    lateinit var activity : MainActivity



    @Test
    fun firstTest(){

        val controller = Robolectric.buildActivity(MainActivity::class.java).create().start()


    }

}