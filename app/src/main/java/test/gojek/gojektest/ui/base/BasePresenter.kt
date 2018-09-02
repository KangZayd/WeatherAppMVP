package test.gojek.gojektest.ui.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter : LifecycleObserver {

    var baseView: BaseView? = null
    val disposables = CompositeDisposable()

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

    fun getView(): BaseView? {
        return baseView
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    fun onPresenterDestroy() {
        disposables.clear()
    }
}