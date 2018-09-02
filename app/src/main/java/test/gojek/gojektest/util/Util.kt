package test.gojek.gojektest.util

import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import test.gojek.gojektest.ui.weather_info.fragment.ErrorFragment
import test.gojek.gojektest.ui.weather_info.fragment.WeatherForecastFragment
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

fun getDayOfWeek(date: String): String {
    var simpleFormatDate = SimpleDateFormat("yyyy-MM-dd")
    var date = simpleFormatDate.parse(date)
    var cal = Calendar.getInstance()
    cal.setTime(date)
    return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
}

fun isToday(date: String): Boolean {
    var simpleFormatDate = SimpleDateFormat("yyyy-MM-dd")
    var date = simpleFormatDate.parse(date)
    var cal = Calendar.getInstance()
    cal.setTime(date)

    return isSameDay(cal, Calendar.getInstance())
}

fun isSameDay(cal1: Calendar?, cal2: Calendar?): Boolean {
    if (cal1 == null || cal2 == null) {
        throw IllegalArgumentException("The dates must not be null")
    }
    return cal1.get(Calendar.ERA) === cal2.get(Calendar.ERA) &&
            cal1.get(Calendar.YEAR) === cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) === cal2.get(Calendar.DAY_OF_YEAR)
}

fun getRotateAnimation(): Animation {

    val rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
    )
    rotate.duration = 700
    rotate.repeatCount = Animation.INFINITE
    rotate.interpolator = LinearInterpolator()

    return rotate
}

fun addDegreeSuperScript(string: String): SpannableString {

    var deg = string + "o"
    var string = SpannableString(deg)
    string.setSpan(SuperscriptSpan(), deg.length - 1, deg.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
    string.setSpan(AbsoluteSizeSpan(50, true), deg.length - 1, deg.length, SpannableString.SPAN_EXCLUSIVE_INCLUSIVE)

    return string
}