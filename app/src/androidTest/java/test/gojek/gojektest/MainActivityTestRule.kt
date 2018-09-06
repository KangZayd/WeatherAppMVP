package test.gojek.gojektest

import android.content.Intent
import android.app.Activity
import android.support.test.rule.ActivityTestRule
import android.util.Log


open class MainActivityTestRule<A : Activity>(activityClass: Class<A>) : ActivityTestRule<A>(activityClass) {
    protected val activityInten1t: Intent
        get() {
            Log.e("MainActivityTestRule", "Prepare the activity's intent")
            return super.getActivityIntent()
        }

    override protected fun beforeActivityLaunched() {
        Log.e("MainActivityTestRule", "Execute before the activity is launched")
        super.beforeActivityLaunched()
    }

    override fun afterActivityLaunched() {
        Log.e("MainActivityTestRule", "Execute after the activity has been launched")
        super.afterActivityLaunched()
    }

    override  fun afterActivityFinished() {
        Log.e("MainActivityTestRule", "Cleanup after it has finished")
        super.afterActivityFinished()
    }

    fun launch(startIntent: Intent): A {
        Log.e("MainActivityTestRule", "Launching the activity")
        return super.launchActivity(startIntent)
    }
}