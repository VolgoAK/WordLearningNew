package xyz.volgoak.data.entities

import DBConstants
import androidx.room.ColumnInfo

class DictionaryInfo {
    @ColumnInfo(name = DBConstants.Info.ALL_WORDS_COUNT)
    var allWords: Int = 0
    @ColumnInfo(name = DBConstants.Info.DICTIONARY_WORDS_COUNT)
    var wordsInDictionary: Int = 0
    @ColumnInfo(name = DBConstants.Info.STUDIED_WORDS_COUNT)
    var learnedWords: Int = 0
}