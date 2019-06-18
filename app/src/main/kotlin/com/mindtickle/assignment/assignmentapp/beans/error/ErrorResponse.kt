package com.mindtickle.assignment.assignmentapp.beans.error

data class ErrorResponse(
        val errorCode: ErrorCodes,
        var errorMessage: String?
)