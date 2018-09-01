package test.gojek.gojektest.ui.base

import android.support.annotation.StringRes



interface BaseView<T> {

    fun showLoading()

    fun hideLoading()

    fun onError(t : T)

    fun onSuccess(t : T)
}