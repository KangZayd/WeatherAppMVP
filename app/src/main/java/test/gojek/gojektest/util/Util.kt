package test.gojek.gojektest.util

import android.transition.Slide
import android.view.Gravity
import test.gojek.gojektest.ui.weather_info.ErrorFragment

fun addErrorAnimation(fragment: ErrorFragment) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        fragment.enterTransition = Slide(Gravity.BOTTOM)
        fragment.exitTransition = Slide(Gravity.BOTTOM)
    }
}