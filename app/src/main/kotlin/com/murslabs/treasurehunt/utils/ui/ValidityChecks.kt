package com.murslabs.treasurehunt.utils.ui

import java.util.regex.Pattern

class ValidityChecks {
    companion object {
        fun isValidEmail(emailStr: String): Boolean {
            val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-zA-Z0-9._-]+"
            val p = Pattern.compile(emailPattern)
            return p.matcher(emailStr).find()
        }
    }
}