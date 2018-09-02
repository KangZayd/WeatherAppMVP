package test.gojek.gojektest.ui.base

import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<v : BaseView, p : BasePresenter> : AppCompatActivity(), BaseView {

    /**
     * using lifecycle registry presenter can be attached to the activities lifecycle
     */
    private val lifecycleRegistry = LifecycleRegistry(this)
    lateinit protected var presenter: p


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
        setContentView(getLayout())
        presenter = initPresenter()
        presenter.attachLifecycle(lifecycleRegistry)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this as BaseView)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachLifecycle(lifecycleRegistry)
    }
}