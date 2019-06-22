package com.murslabs.treasurehunt.utils

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import com.murslabs.treasurehunt.R

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