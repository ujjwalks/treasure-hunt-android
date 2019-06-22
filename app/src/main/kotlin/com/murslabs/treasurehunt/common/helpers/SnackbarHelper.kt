package com.murslabs.treasurehunt.common.helpers

import android.app.Activity
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import android.view.View

/**
 * Helper to manage the sample snackbar. Hides the Android boilerplate code, and exposes simpler
 * methods.
 */
class SnackbarHelper {
    private var messageSnackbar: Snackbar? = null

    val isShowing: Boolean
        get() = messageSnackbar != null

    private enum class DismissBehavior {
        HIDE,
        SHOW,
        FINISH
    }

    /** Shows a snackbar with a given message.  */
    fun showMessage(activity: FragmentActivity?, message: String) {
        activity?.let { act ->
            show(act, message, DismissBehavior.HIDE)
        }
    }

    /** Shows a snackbar with a given message, and a dismiss button.  */
    fun showMessageWithDismiss(activity: FragmentActivity?, message: String) {
        activity?.let { act ->
            show(act, message, DismissBehavior.SHOW)
        }
    }

    /**
     * Shows a snackbar with a given error message. When dismissed, will finish the activity. Useful
     * for notifying errors, where no further interaction with the activity is possible.
     */
    fun showError(activity: FragmentActivity?, errorMessage: String) {
        activity?.let { act ->
            show(act, errorMessage, DismissBehavior.FINISH)
        }
    }

    /**
     * Hides the currently showing snackbar, if there is one. Safe to call from any thread. Safe to
     * call even if snackbar is not shown.
     */
    fun hide(activity: FragmentActivity) {
        activity.runOnUiThread {
            if (messageSnackbar != null) {
                messageSnackbar!!.dismiss()
            }
            messageSnackbar = null
        }
    }

    private fun show(
            activity: FragmentActivity, message: String, dismissBehavior: DismissBehavior) {
        activity.runOnUiThread(
                object : Runnable {
                    override fun run() {
                        messageSnackbar = Snackbar.make(
                                activity.findViewById(android.R.id.content),
                                message,
                                Snackbar.LENGTH_INDEFINITE)
                        messageSnackbar!!.view.setBackgroundColor(BACKGROUND_COLOR)
                        if (dismissBehavior != DismissBehavior.HIDE) {
                            messageSnackbar!!.setAction(
                                    "Dismiss",
                                    object : View.OnClickListener {
                                        override fun onClick(v: View) {
                                            messageSnackbar!!.dismiss()
                                        }
                                    })
                            if (dismissBehavior == DismissBehavior.FINISH) {
                                messageSnackbar!!.addCallback(
                                        object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                                            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                                super.onDismissed(transientBottomBar, event)
                                                activity.finish()
                                            }
                                        })
                            }
                        }
                        messageSnackbar!!.show()
                    }
                })
    }

    companion object {
        private val BACKGROUND_COLOR = -0x40cdcdce
        val instance = SnackbarHelper()
    }
}