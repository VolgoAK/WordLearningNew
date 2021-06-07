package xyz.volgoak.navigation

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import xyz.volgoak.datacore.utils.TextContainer
import xyz.volgoak.navigation.screens.BaseAppScreen


interface NavigatorContainer {
    fun showAlertDialog(dialog: AlertDialog)

    fun dismissDialogFragment(tag: String)

    fun showDialogFragment(
        dialog: DialogFragment,
        tag: String
    )

    fun showToast(text: TextContainer)

    fun showSnackBar(
        text: TextContainer,
        actionText: TextContainer?,
        action: (() -> Unit)?,
        dismissAction: (() -> Unit)?
    )

    fun handleError(
        throwable: Throwable,
        requiredAuthRedirect: Boolean
    ): BaseAppScreen?
}