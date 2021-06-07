package xyz.volgoak.datacore.errorhandling

interface IErrorHandler {

    fun parseThrowable(throwable: Throwable): ApiException
}