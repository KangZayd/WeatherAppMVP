package test.demo.weatherapp.util

import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.SuperscriptSpan
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import test.demo.weatherapp.appContext
import test.demo.weatherapp.ui.weather_info.fragment.ErrorFragment
import test.demo.weatherapp.ui.weather_info.fragment.WeatherForecastFragment
import java.text.SimpleDateFormat
import java.util.*
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationSet
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView


fun animate(imageView: ImageView, images: IntArray, imageIndex: Int, forever: Boolean) {

    //imageView <-- The View which displays the images
    //images[] <-- Holds R references to the images to display
    //imageIndex <-- index of the first image to show in images[]
    //forever <-- If equals true then after the last image it starts all over again with the first image resulting in an infinite loop. You have been warned.

    val fadeInDuration = 500 // Configure time values here
    val timeBetween = 3000
    val fadeOutDuration = 1000

    imageView.setVisibility(View.INVISIBLE)    //Visible or invisible by default - this will apply when the animation ends
    imageView.setImageResource(images[imageIndex])

    val fadeIn = AlphaAnimation(0f, 1f)
    fadeIn.interpolator = DecelerateInterpolator() // add this
    fadeIn.duration = fadeInDuration.toLong()

    val fadeOut = AlphaAnimation(1f, 0f)
    fadeOut.interpolator = AccelerateInterpolator() // and this
    fadeOut.startOffset = (fadeInDuration + timeBetween).toLong()
    fadeOut.duration = fadeOutDuration.toLong()

    val animation = AnimationSet(false) // change to false
    animation.addAnimation(fadeIn)
    animation.addAnimation(fadeOut)
    animation.repeatCount = 1
    imageView.setAnimation(animation)

    animation.setAnimationListener(object : AnimationListener {
        override fun onAnimationEnd(animation: Animation) {
            if (images.size - 1 > imageIndex) {
                animate(imageView, images, imageIndex + 1, forever) //Calls itself until it gets to the end of the array
            } else {
                if (forever) {
                    animate(imageView, images, 0, forever)  //Calls itself to start the animation all over again in a loop if forever = true
                }
            }
        }

        override fun onAnimationRepeat(animation: Animation) {
            // TODO Auto-generated method stub
        }

        override fun onAnimationStart(animation: Animation) {
            // TODO Auto-generated method stub
        }
    })
}

fun getString(resId: Int): String {
    return appContext.getString(resId)
}

fun getImage(resId: Int): Drawable {
    return appContext.resources.getDrawable(resId)
}

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