package xyz.volgoak.navigation.commands

import xyz.volgoak.datacore.utils.TextContainer
import ru.terrakok.cicerone.commands.Command

internal data class ShareTextCommand(
    val text: TextContainer,
    val title: TextContainer
) : Command