package com.mindtickle.assignment.assignmentapp.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment

/**
 * Created by Ujjwal on 06/02/18.
 */

/**
 * Base Class for activity without view pagers
 */
abstract class BaseFragmentActivity<B : ViewDataBinding> : BaseActivity<B>() {

    /**
     * @setFragment initialize view for current coordinator
     * @resId id of resource where fragment needs to be added
     * @fragment instance of fragment being added
     */
    fun setFragment(@IdRes resID: Int, fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .add(resID, fragment)
                .commit()
    }

    /* to navigate between the fragments for a coordinator */
    protected fun replaceFragmentInternal(@IdRes containerId: Int,
                                          fragment: Fragment, fragmentTag: String?, args: Bundle?,
                                          addToBackstack: Boolean, backstackTag: String?) {

        val fm = supportFragmentManager
        if (args != null) {
            fragment.arguments = args
        }
        val ft = fm.beginTransaction().replace(containerId, fragment, fragmentTag)
        if (addToBackstack) {
            ft.addToBackStack(backstackTag).commit()
            fm.executePendingTransactions()
        } else {
            ft.commitNow()
        }
    }
}