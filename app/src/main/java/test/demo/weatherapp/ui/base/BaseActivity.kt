package test.demo.weatherapp.ui.base

import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity< p : BasePresenter> : AppCompatActivity() {

    /**
     * using lifecycle registry presenter can be attached to the activities lifecycle
     */
    private val lifecycleRegistry = LifecycleRegistry(this)

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    @Inject lateinit var presenter: p

    protected abstract fun init()

    /**
     * get the layout of the activity
     *
     * @return
     */
    protected abstract fun getLayout(): Int

    /**
     * initialize the presenter
     *
     * @return
     */
    protected abstract fun initPresenter(): p

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContentView(getLayout())
//        presenter = initPresenter()
        presenter.attachLifecycle(lifecycleRegistry)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachLifecycle(lifecycleRegistry)
    }
}