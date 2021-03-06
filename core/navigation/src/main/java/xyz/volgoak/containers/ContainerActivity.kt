package xyz.volgoak.navigation.containers

import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import xyz.volgoak.datacore.utils.TextContainer
import xyz.volgoak.navigation.navigator.holder.AppNavigatorHolder
import xyz.volgoak.navigation.BackButtonListener
import xyz.volgoak.navigator.BaseNavigator
import xyz.volgoak.navigation.NavigatorContainer
import xyz.volgoak.uicore.dismissDialogFragmentIfShown
import xyz.volgoak.uicore.toast

abstract class ContainerActivity : AppCompatActivity(), NavigatorContainer {

    abstract val navigator: BaseNavigator

    abstract val containerId: Int

    abstract val navigatorHolder: AppNavigatorHolder

    abstract fun getSnackBarContainer(): ViewGroup

    private var shownDialog: AlertDialog? = null

    override fun onResume() {
        super.onResume()
        navigator.setNavigator(navigatorHolder)
    }

    override fun onPause() {
        super.onPause()
        navigator.removeNavigator()
    }

    override fun onStop() {
        super.onStop()
        shownDialog?.dismiss()
        shownDialog = null
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(containerId)
        val backHandled = (fragment as? BackButtonListener)?.onBackPressed() ?: false
        if (!backHandled) super.onBackPressed()
    }

    override fun showAlertDialog(dialog: AlertDialog) {
        if (shownDialog?.isShowing == true) {
            shownDialog?.dismiss()
        }

        dialog.show()
        shownDialog = dialog
    }

    override fun dismissDialogFragment(tag: String) {
        dismissDialogFragmentIfShown(tag)
    }

    override fun showDialogFragment(dialog: DialogFragment, tag: String) {
        dismissDialogFragment(tag)
        dialog.show(supportFragmentManager, tag)
    }

    override fun showToast(text: TextContainer) {
        toast(text.getTextValue(this))
    }

    override fun showSnackBar(
        text: TextContainer,
        actionText: TextContainer?,
        action: (() -> Unit)?,
        dismissAction: (() -> Unit)?
    ) {
        val snackbar = Snackbar.make(
            getSnackBarContainer(), text.getTextValue(this), Snackbar.LENGTH_SHORT
        )

        action?.let {
            snackbar.setAction(actionText?.getTextValue(this)) { _ -> it.invoke() }
        }

        dismissAction?.let {
            snackbar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(
                    transientBottomBar: Snackbar?,
                    event: Int
                ) {
                    super.onDismissed(transientBottomBar, event)
                    it.invoke()
                }
            })
        }

        snackbar.show()
    }
}