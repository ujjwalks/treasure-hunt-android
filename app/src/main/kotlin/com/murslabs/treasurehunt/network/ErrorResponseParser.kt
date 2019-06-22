package com.murslabs.treasurehunt.network

import com.google.gson.Gson
import com.murslabs.treasurehunt.beans.error.ErrorCodes
import com.murslabs.treasurehunt.beans.error.ErrorResponse
import com.murslabs.treasurehunt.dependency.scope.PerApplication
import retrofit2.HttpException
import javax.inject.Inject

@PerApplication
class ErrorResponseParser @Inject constructor(val gson: Gson) {

    fun parse(httpException: HttpException): ErrorResponse {
        val errorBody = httpException.response().errorBody()
        return errorBody?.run {
            val errorString = String(bytes())
            return gson.fromJson(errorString, ErrorResponse::class.java)
        } ?: ErrorResponse(ErrorCodes.CONNECTION_ERROR, "")
    }
}