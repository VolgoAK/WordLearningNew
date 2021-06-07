package xyz.volgoak.datacore.errorhandling

import xyz.volgoak.datacore.utils.TextContainer
import java.io.IOException

open class ExceptionWithMessage(
    val messageContainer: TextContainer,
    val titleContainer: TextContainer
) : IOException()