package test.demo.weatherapp.data.usecases

import io.reactivex.Flowable

interface Interactor<T> {

    fun execute() : Flowable<T>
}