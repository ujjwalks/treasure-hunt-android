package com.murslabs.treasurehunt.base

import android.app.Dialog
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.annotation.VisibleForTesting
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.murslabs.treasurehunt.dependency.utils.Injectable
import com.murslabs.treasurehunt.utils.ui.AppDataBindingComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel> : Fragment(), Injectable {

    internal var dataBindingComponent = AppDataBindingComponent()
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    var binding: B? = null

    protected var viewModel: V? = null

    private var loadingDialog: Dialog? = null

    protected lateinit var compositeDisposable: CompositeDisposable

    abstract fun handleError(error: BaseError)

    private val errorHandler = Consumer<BaseError> { error ->
        when (error) {
            Error.AUTHENTICATION -> {
                showErrorSnackBar(error)
            }
            Error.NOINTERNET -> {
                showErrorSnackBar(error)
            }
            Error.UNEXCECTED -> {
                showErrorSnackBar(error)
            }
            else -> {
                handleError(error)
            }
        }
    }

    open fun initializeListeners() {
        viewModel?.apply {
            compositeDisposable.add(
                    onError.subscribe(errorHandler)
            )
        }
    }

    protected fun showErrorSnackBar(error: BaseError) {
        val snackbar = Snackbar.make(view!!, error.getErrorMessageWithContext(context!!), Snackbar.LENGTH_SHORT)
        if (error.actionTitle != null) {
            snackbar.setAction(error.actionTitle, { error.errorAction.run() })
        }
        snackbar.show()
    }

    fun showErrorToast(error: BaseError) {
        var errorMessage = ""
        if (error.errorMessage != null) {
            errorMessage = error.errorMessage!!
        }
        if (error.errorMessageResId != null) {
            errorMessage = context!!.getString(error.errorMessageResId!!)
        }

        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable = CompositeDisposable()
        initializeListeners()
    }

    override fun onPause() {
        compositeDisposable.dispose()
        super.onPause()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        viewModel = null
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
    }

    /* Sets the content view, creates the binding and attaches the view to the view model */
    protected fun setAndBindContentView(inflater: LayoutInflater, container: ViewGroup?,
                                        savedInstanceState: Bundle?, @LayoutRes layoutResID: Int): View {

        binding = DataBindingUtil.inflate(inflater, layoutResID, container, false)
        DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutResID,
                container, false, dataBindingComponent)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (viewModel == null) {
            throw IllegalStateException("viewModel must already be set via calling setViewModel")
        }
    }

    /* Sets the view model, creates the binding and attaches the view to the view model */
    protected fun setViewModel(modelClass: Class<V>, factory: ViewModelProvider.Factory) {
        viewModel = ViewModelProviders.of(this, factory).get(modelClass)
    }

    /* to show loading dialog with proper message */
    protected fun showLoadingDialog(@StringRes message: Int, @StringRes title: Int) {
        hideLoadingDialog()
        loadingDialog = indeterminateProgressDialog(message, title)
    }

    /* to hide progress dialog if it is showing */
    protected fun hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
        }
    }
}
