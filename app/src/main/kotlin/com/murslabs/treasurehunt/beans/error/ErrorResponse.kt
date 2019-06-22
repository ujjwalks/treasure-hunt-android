package com.murslabs.treasurehunt.beans.error

data class ErrorResponse(
        val errorCode: ErrorCodes,
        var errorMessage: String?
)