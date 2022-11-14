package com.Alkemy.alkemybankbase.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import retrofit2.HttpException
import java.io.IOException

sealed class Error {

    data class Server(val message: String) : Error()
    object Connection : Error()
    data class UnknownError(val message: String) : Error()
}

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connection
    is HttpException -> Error.Server(message ?: "")
    else -> Error.UnknownError(message ?: "")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}