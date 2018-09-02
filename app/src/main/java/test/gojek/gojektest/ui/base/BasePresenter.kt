package test.gojek.gojektest.ui.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver

abstract class BasePresenter : LifecycleObserver {

    var baseView: BaseView? = null

    fun attachLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    fun detachLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    fun attachView(view: BaseView) {
        baseView = view
    }

    fun detachView() {
        baseView = null
    }

    fun getView(): BaseView?{
        return baseView
    }

    fun onPresenterDestroy(){

    }
}