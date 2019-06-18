package com.mindtickle.assignment.assignmentapp.utils

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.AutoCompleteTextView
import com.mindtickle.assignment.assignmentapp.R
import com.mindtickle.assignment.assignmentapp.utils.isValidEmail
import com.viewpagerindicator.CirclePageIndicator

class ViewBindingUtils {
    companion object {

        @JvmStatic
        @BindingAdapter("visibility")
        fun setButtonVisibility(view: View, isVisible: Boolean) {
            if (isVisible)
                view.visibility = View.VISIBLE
            else view.visibility = View.GONE
        }


        @JvmStatic
        @BindingAdapter("loginOptionsFragmentEmailEt", "loginOptionTextInputLayout")
        fun bindErrorMethod(editText: TextInputEditText, inputLayout: TextInputLayout, text: String) {
            if (!isValidEmail(text)) {
                inputLayout.error = inputLayout.context.getString(R.string.error_enter_valid_email)
            }
        }
    }
}