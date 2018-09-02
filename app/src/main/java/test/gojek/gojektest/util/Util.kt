package test.gojek.gojektest.util

import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import test.gojek.gojektest.ui.weather_info.ErrorFragment
import test.gojek.gojektest.ui.weather_info.WeatherForecastFragment
import java.text.SimpleDateFormat
import java.util.*

fun addErrorAnimation(fragment: ErrorFragment) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        fragment.enterTransition = Slide(Gravity.BOTTOM)
        fragment.exitTransition = Slide(Gravity.BOTTOM)
    }
}
fun addWeatherScreenAnimation(fragment: WeatherForecastFragment) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        fragment.enterTransition = Fade()
    }
}

fun getDayOfWeek(date : String) : String{
    var simpleFormatDate = SimpleDateFormat("yyyy-MM-dd")
    var date = simpleFormatDate.parse(date)
    var cal = Calendar.getInstance()
    cal.setTime(date)
    return cal.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,Locale.getDefault())
}

fun isToday(date : String) : Boolean{
    var simpleFormatDate = SimpleDateFormat("yyyy-MM-dd")
    var date = simpleFormatDate.parse(date)
    var cal = Calendar.getInstance()
    cal.setTime(date)

    return isSameDay(cal,Calendar.getInstance())
}

fun isSameDay(cal1: Calendar?, cal2: Calendar?): Boolean {
    if (cal1 == null || cal2 == null) {
        throw IllegalArgumentException("The dates must not be null")
    }
    return cal1.get(Calendar.ERA) === cal2.get(Calendar.ERA) &&
            cal1.get(Calendar.YEAR) === cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) === cal2.get(Calendar.DAY_OF_YEAR)
}