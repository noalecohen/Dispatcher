package com.noalecohen.dispatcher.model.response

data class ErrorResponse(
    val status: String,
    val code: String,
    val message: String,
)