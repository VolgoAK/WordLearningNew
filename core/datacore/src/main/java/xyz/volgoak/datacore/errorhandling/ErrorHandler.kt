package xyz.volgoak.datacore.errorhandling

import retrofit2.HttpException
import java.net.UnknownHostException

abstract class ErrorHandler : IErrorHandler {

    override fun parseThrowable(throwable: Throwable): ApiException {
        val apiError = if (throwable is HttpException) {
            val message = parseErrorMessage(throwable)
            convertMessageToApiError(message, throwable)
        } else null

        return apiError ?: when (throwable) {
            is UnknownHostException -> NoInternetException(throwable)
            else -> UnknownException(throwable)
        }
    }

    abstract fun parseErrorMessage(throwable: HttpException): ApiErrorInfo?

    abstract fun convertMessageToApiError(info: ApiErrorInfo?, reason: HttpException): ApiException?
}