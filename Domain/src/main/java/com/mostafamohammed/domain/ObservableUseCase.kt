package com.mostafamohammed.domain

import com.mostafamohammed.domain.executer.PostExecutionThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class ObservableUseCase<T: DomainModel,in Params> constructor(private val postExecutionThread: PostExecutionThread){

    private val disposables = CompositeDisposable()
    abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params?){
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable){
        disposables.add(disposable)
    }

    fun dispose(){
        disposables.dispose()
    }
}