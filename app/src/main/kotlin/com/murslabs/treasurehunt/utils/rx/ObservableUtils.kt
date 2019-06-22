package com.murslabs.treasurehunt.utils.rx

import android.databinding.Observable.OnPropertyChangedCallback
import android.databinding.ObservableField
import com.murslabs.treasurehunt.beans.Result
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeUnit

private const val defaultRetryCount = 3
private val defaultRetryStrategy = Function<Int, ObservableSource<Long>> { retryCount ->
    Observable.timer(Math.pow(5.0, retryCount.toDouble()).toLong(), TimeUnit.SECONDS)
}

// default computation scheduler
fun <T> Observable<T>.applyComputationMainSchedulers(): Observable<T> {
    return subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
}

// default database scheduler
fun <T> Observable<T>.applyIOMainSchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

// default database service scheduler
fun <T> Observable<T>.applyIOSchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
}

// default database service scheduler
fun <T> Observable<T>.applyImmediateSchedulers(): Observable<T> {
    return subscribeOn(Schedulers.trampoline()).observeOn(Schedulers.trampoline())
}

fun <T> Flowable<T>.applyImmediateSchedulers(): Flowable<T> {
    return subscribeOn(Schedulers.trampoline()).observeOn(Schedulers.trampoline())
}

fun <T> Observable<T>.withRetry(): Observable<T> {
    return this.withRetry(defaultRetryStrategy, defaultRetryCount)
}

fun <T> Observable<T>.withRetry(retryCount: Int): Observable<T> {
    return this.withRetry(defaultRetryStrategy, retryCount)
}

fun <T> Observable<T>.withRetry(function: Function<Int, ObservableSource<Long>>, retryCount: Int): Observable<T> {
    return this.retryWhen(
            { errors ->
                errors.flatMap({
                    if (it is IOException) {
                        Observable.just(it)
                    } else {
                        Observable.error(it)
                    }
                }).zipWith<Int, Int>(Observable.range(1, retryCount),
                        BiFunction<Throwable, Int, Int> { throwable, count ->
                            if (count < retryCount) count
                            else throw Exceptions.propagate(throwable)
                        }).flatMap(function)
            }
    )
}

fun <T> ObservableField<T>.toObservable(): Observable<T> {
    return Observable.create { emitter ->
        if (!emitter.isDisposed) {
            this.get()?.apply { emitter.onNext(this) }
        }
        val callback = object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(dataBindingObservable: android.databinding.Observable, propertyId: Int) {
                if (dataBindingObservable === this) {
                    this@toObservable.get()?.apply { emitter.onNext(this) }
                }
            }
        }
        this.addOnPropertyChangedCallback(callback)
        emitter.setCancellable { this.removeOnPropertyChangedCallback(callback) }
    }
}

fun <T> Observable<T>.transformErrorToResult(): Observable<Result<T>> {
    return this.map { it.asResult() }
            .onErrorReturn { it.asErrorResult() }
}

fun <T> T.asResult(): Result<T> {
    return Result.Success(data = this)
}

fun <T> Throwable.asErrorResult(): Result<T> {
    return Result.Error(throwable = this)
}