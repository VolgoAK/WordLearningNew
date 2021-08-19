package xyz.volgoak.data.dbimport.model

import DBConstants
import xyz.volgoak.data.entities.Theme
import xyz.volgoak.data.entities.Word
import xyz.volgoak.data.entities.WordsSet

fun ThemeNt.toDb() = Theme(
    0, name, code
)

fun WordSetNt.toDb() = WordsSet(
    0,
    name,
    description,
    imageUrl
)

fun WordNt.toDb() = Word(
    0,
    word, translation, transcription,
    DBConstants.Words.OUT_OF_DICTIONARY,
    System.currentTimeMillis(),
    0
)