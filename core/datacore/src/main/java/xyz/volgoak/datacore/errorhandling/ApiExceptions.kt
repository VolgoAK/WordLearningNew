package xyz.volgoak.datacore.errorhandling

import xyz.volgoak.datacore.R
import xyz.volgoak.datacore.utils.TextContainer

object ApiErrorMessages {
    const val WRONG_CREDENTIALS = "WRONG_CREDENTIALS"
    const val WRONG_PASSWORD = "WRONG_PASSWORD"
    const val USER_NOT_VERIFIED = "USER_NOT_VERIFIED"
    const val USER_WAS_BLOCKED = "USER_WAS_BLOCKED"
    const val USER_NOT_FOUND = "USER_NOT_FOUND"
    const val USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS"
    const val USER_NOT_SUBSCRIBED = "USER_NOT_SUBSCRIBED"
}

abstract class ApiException(
    messageContainer: TextContainer,
    titleContainer: TextContainer,
    val reason: Throwable
) :
    ExceptionWithMessage(messageContainer, titleContainer)

class NoInternetException(reason: Throwable) :
    ApiException(
        TextContainer.ResContainer(R.string.data_core_no_internet),
        TextContainer.ResContainer(R.string.data_core_common_error_title),
        reason
    )

class UnknownApiException(reason: Throwable) :
    ApiException(
        TextContainer.ResContainer(R.string.data_core_network_error),
        TextContainer.ResContainer(R.string.data_core_common_error_title),
        reason
    )

class UnknownException(reason: Throwable) :
    ApiException(
        TextContainer.ResContainer(R.string.data_core_unknown_exception),
        TextContainer.ResContainer(R.string.data_core_common_error_title),
        reason
    )