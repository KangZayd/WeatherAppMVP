package test.gojek.gojektest.util

import android.os.Build
import android.support.v7.app.AppCompatActivity
import test.gojek.gojektest.R

fun AppCompatActivity.changeStatusBarColor(color : Int){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window?.setStatusBarColor(getResources().getColor(color))
    }
}