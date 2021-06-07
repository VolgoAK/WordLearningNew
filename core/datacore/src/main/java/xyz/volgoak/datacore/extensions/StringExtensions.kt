package xyz.volgoak.datacore.extensions

fun String.ellipsize(maxLength: Int, ellipsizeChar: Char = '…') = when {
    length <= maxLength -> this
    maxLength == 0 -> ""
    else -> substring(0, maxLength - 1) + ellipsizeChar
}