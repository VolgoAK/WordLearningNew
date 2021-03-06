package xyz.volgoak.navigation.navigator

import android.content.DialogInterface
import xyz.volgoak.datacore.errorhandling.ApiException
import xyz.volgoak.datacore.utils.TextContainer
import xyz.volgoak.navigation.screens.BaseAppScreen
import xyz.volgoak.navigator.BaseNavigator

class ScopedNavigator(
    private val parentNavigator: BaseNavigator,
    private val scopeName: String
) : BaseNavigator() {

    override fun showErrorWithRetryDialog(exception: ApiException, retryAction: () -> Unit) {
        parentNavigator.showErrorWithRetryDialog(exception, retryAction)
    }

    override fun showDialog(
        title: TextContainer?,
        message: TextContainer?,
        positiveButtonText: TextContainer?,
        neutralButtonText: TextContainer?,
        negativeButtonText: TextContainer?,
        positiveButtonAction: ((DialogInterface, Int) -> Unit)?,
        neutralButtonAction: ((DialogInterface, Int) -> Unit)?,
        negativeButtonAction: ((DialogInterface, Int) -> Unit)?,
        onDismissAction: ((DialogInterface) -> Unit)?,
        onCancelAction: ((DialogInterface) -> Unit)?,
        cancelable: Boolean
    ) {
        parentNavigator.showDialog(
            title,
            message,
            positiveButtonText,
            neutralButtonText,
            negativeButtonText,
            positiveButtonAction,
            neutralButtonAction,
            negativeButtonAction,
            onDismissAction,
            onCancelAction,
            cancelable
        )
    }

    override fun showToast(text: TextContainer) {
        parentNavigator.showToast(text)
    }

    override fun showSnackBar(
        text: TextContainer,
        action: (() -> Unit)?,
        actionText: TextContainer?,
        onDismissAction: (() -> Unit)?
    ) {
        parentNavigator.showSnackBar(text, action, actionText, onDismissAction)
    }

    override fun handleError(error: Throwable, handleAuth: Boolean) {
        parentNavigator.handleError(error, handleAuth)
    }

    fun setParentRootScreen(screen: BaseAppScreen) {
        parentNavigator.setRootScreen(screen)
    }

    fun parentNavigateTo(screen: BaseAppScreen) {
        parentNavigator.navigateTo(screen)
    }
}