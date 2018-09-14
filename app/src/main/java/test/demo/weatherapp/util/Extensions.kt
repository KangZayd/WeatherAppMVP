package test.demo.weatherapp.util

import android.os.Build
import android.support.v7.app.AppCompatActivity
import test.demo.weatherapp.R

fun AppCompatActivity.changeStatusBarColor(color : Int){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window?.setStatusBarColor(getResources().getColor(color))
    }
}