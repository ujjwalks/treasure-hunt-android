package com.mindtickle.assignment.assignmentapp.base

import android.content.Context
import android.support.annotation.StringRes
import com.mindtickle.assignment.assignmentapp.R
import io.reactivex.functions.Action
import timber.log.Timber

abstract class BaseError {
    constructor(errorAction: Action?) {
        if (errorAction != null) {
            this.errorAction = errorAction
        }
    }

    constructor(errorMessage: String,
                actionTitle: String? = null,
                action: Action? = null) : this(action) {
        this.actionTitle = actionTitle
        this.errorMessage = errorMessage
    }

    constructor(@StringRes errorMessage: Int,
                @StringRes actionTitle: Int? = null,
                action: Action? = null) : this(action) {
        this.errorMessageResId = errorMessage
        this.actionTitleResId = actionTitle
    }

    var errorMessage: String? = null
    var errorMessageResId: Int? = null

    var actionTitle: String? = null
    var actionTitleResId: Int? = null

    var errorAction: Action = Action {
        Timber.e("Base error no Action provides")
    }

    fun getErrorMessageWithContext(context: Context): String {
        return errorMessage?.let { it }
                ?: context.getString(errorMessageResId?.let { it }
                        ?: R.string.error_unexpected)
    }
}