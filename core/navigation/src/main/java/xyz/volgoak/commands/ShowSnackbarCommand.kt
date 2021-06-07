package xyz.volgoak.navigation.commands

import xyz.volgoak.datacore.utils.TextContainer
import ru.terrakok.cicerone.commands.Command

internal class ShowSnackbarCommand(
    val messageText: TextContainer,
    val actionText: TextContainer?,
    val action: (() -> Unit)?,
    val onDismissAction: (() -> Unit)?
) : Command