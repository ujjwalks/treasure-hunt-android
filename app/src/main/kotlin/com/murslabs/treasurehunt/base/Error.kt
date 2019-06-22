package com.murslabs.treasurehunt.base

import com.murslabs.treasurehunt.R
import io.reactivex.functions.Action

sealed class Error {
    object AUTHENTICATION : BaseError(R.string.error_authentication_required, R.string.login, Action {
    })

    object NOINTERNET : BaseError(R.string.error_no_internet)
    object UNEXCECTED : BaseError(R.string.error_unexpected)
    object INTERNAL_SERVER_ERROR : BaseError(R.string.error_unexpected)
    object INVALID_EMAIL_ID : BaseError(R.string.error_enter_valid_email)
}

sealed class LoginError : Error() {
    object DEACTIVATED_LEARNER : BaseError(R.string.deactivated_learner)
    object LEARNER_NOT_AUTHORISED : BaseError(R.string.learner_not_authorised)
    object ERROR_ACTIVATING_LEARNER : BaseError(R.string.error_activating_learner)
    object ERROR_CREATING_SESSION : BaseError(R.string.error_creating_session)
    object LOGIN_FAILED : BaseError(R.string.login_failed)
    object INVALID_GOOGLE_CODE : BaseError(R.string.invalid_google_code)
    object INVALID_RESPONSE : BaseError(R.string.user_not_authorised)
    object INVALID_APIKEY_SIMPLESSO : BaseError(R.string.invalid_apikey_simplesso)
    object USER_NOT_AUTHORISED : BaseError(R.string.something_went_wrong)
    object ERROR_FETCHING_USER : BaseError(R.string.deactivated_learner)
    object INVALID_URL : BaseError(R.string.error_invalid_company_url)
    object INVALID_EMAIL : BaseError(R.string.invalid_email)
    object INVALID_USER_NAME_PASSWORD_ERROR : BaseError(R.string.incorrect_email_or_password)
    object INVALID_PASSWORD : BaseError(R.string.invalid_password)
    object ACCOUNT_DEACTIVATED : BaseError(R.string.error_account_deactivate_message)
    object NO_USER : BaseError(R.string.user_not_registered)
}
