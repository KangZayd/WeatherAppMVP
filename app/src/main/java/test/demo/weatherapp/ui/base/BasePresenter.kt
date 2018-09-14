package test.demo.weatherapp.ui.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.annotation.VisibleForTesting
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter : LifecycleObserver {


    val disposables = CompositeDisposable()

    fun attachLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    fun detachLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    fun onPresenterDestroy() {
        disposables.clear()
    }
}