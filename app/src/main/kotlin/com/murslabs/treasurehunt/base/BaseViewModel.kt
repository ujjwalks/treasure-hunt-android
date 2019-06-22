package com.murslabs.treasurehunt.base

import android.arch.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import com.murslabs.treasurehunt.beans.error.ErrorCodes
import com.murslabs.treasurehunt.exceptions.AuthenticationRequiredException
import com.murslabs.treasurehunt.exceptions.InternalServerException
import com.murslabs.treasurehunt.exceptions.NoNetworkException
import com.murslabs.treasurehunt.network.ErrorResponseParser
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    var isLoading: PublishRelay<Boolean> = PublishRelay.create()

    var onError: PublishRelay<BaseError> = PublishRelay.create()

    var compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var errorResponseParser: ErrorResponseParser

    /* Atomically clears the container, then disposes all the previously contained Disposables. */
    abstract fun refresh()

    override fun onCleared() {
        compositeDisposable.dispose() // Dispose the resource
        super.onCleared()
    }

    protected val errorConsumer = Consumer<Throwable> { throwable ->
        val error = transformExceptionToError(transformThrowable(throwable))

        isLoading.accept(false)
        error?.apply {
            onError.accept(error)
        } ?: onError.accept(getTransformedError(throwable))
    }

    private fun transformThrowable(throwable: Throwable): Throwable {
        val causeThrowable: Throwable =
                if (throwable.javaClass == RuntimeException::class.java && throwable.cause != null) {
                    throwable.cause!!
                } else {
                    throwable
                }

        return when {
            causeThrowable.javaClass == HttpException::class.java ->
                when ((causeThrowable as HttpException).code()) {
                    500 -> InternalServerException(causeThrowable)
                    else -> causeThrowable
                }
            causeThrowable.javaClass == IOException::class.java -> NoNetworkException(causeThrowable)
            else -> causeThrowable
        }
    }

    private fun getTransformedError(throwable: Throwable?): BaseError? {
        return when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    401 -> {
                        val errorResponse = errorResponseParser.parse(throwable)
                        return when (errorResponse.errorCode) {
                            ErrorCodes.ACCESS_DENIED ->
                                LoginError.USER_NOT_AUTHORISED
                            else -> {
                                Error.AUTHENTICATION
                            }
                        }
                    }
                    400, 500 -> Error.INTERNAL_SERVER_ERROR
                    else -> {
                        return Error.AUTHENTICATION
                    }
                }
            }
            is AuthenticationRequiredException -> {
                Error.AUTHENTICATION
            }
            is NoNetworkException -> {
                Error.NOINTERNET
            }
            is InternalServerException -> {
                Error.INTERNAL_SERVER_ERROR
            }
            else -> Error.UNEXCECTED
        }
    }

    protected open fun transformExceptionToError(throwable: Throwable): BaseError? {
        return null
    }
}