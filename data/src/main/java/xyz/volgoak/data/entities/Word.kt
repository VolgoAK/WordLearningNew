package xyz.volgoak.data.entities

import DBConstants
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBConstants.Words.TABLE_NAME)
data class Word(
    @ColumnInfo(name = DBConstants.BASE_ID, index = true)
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = DBConstants.Words.COLUMN_WORD)
    val word: String,
    @ColumnInfo(name = DBConstants.Words.COLUMN_TRANSLATION)
    val translation: String,
    @ColumnInfo(name = DBConstants.Words.COLUMN_TRANSCRIPTION)
    val transcription: String?,
    /*@ColumnInfo(name = DBConstants.Words.COLUMN_TRAINED_WT)
    val trainedWt: Int,
    @ColumnInfo(name = DBConstants.Words.COLUMN_TRAINED_TW)
    val trainedTw: Int,
    @ColumnInfo(name = DBConstants.Words.COLUMN_STUDIED)
    val studied: Int,*/
    @ColumnInfo(name = DBConstants.Words.COLUMN_STATUS)
    val status: Int,
    @ColumnInfo(name = DBConstants.Words.COLUMN_ADDED_TIME)
    val addedTime: Long,
    @ColumnInfo(name = DBConstants.Words.COLUMN_LAST_TRAINED_TIME)
    val lastTrained: Long
) {

    val isInDictionary: Boolean
        get() = status == DBConstants.Words.IN_DICTIONARY
}