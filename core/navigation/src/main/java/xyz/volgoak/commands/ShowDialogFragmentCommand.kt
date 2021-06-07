package xyz.volgoak.navigation.commands

import xyz.volgoak.navigation.screens.DialogFragmentScreen
import ru.terrakok.cicerone.commands.Command

internal class ShowDialogFragmentCommand(
    val screen: DialogFragmentScreen
) : Command