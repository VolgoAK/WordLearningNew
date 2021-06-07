package xyz.volgoak.navigation.commands

import xyz.volgoak.datacore.utils.TextContainer
import ru.terrakok.cicerone.commands.Command

internal class ShowToastCommand(
    val message: TextContainer
) : Command