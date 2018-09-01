package test.gojek.gojektest.data.usecases

import io.reactivex.Flowable

interface Interactor<T> {

    fun execute() : Flowable<T>
}