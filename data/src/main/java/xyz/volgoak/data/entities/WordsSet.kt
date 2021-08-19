package xyz.volgoak.data.entities

import DBConstants
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBConstants.Sets.TABLE_NAME)
data class WordsSet(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstants.BASE_ID, index = true)
    var id: Long = 0,
    @ColumnInfo(name = DBConstants.Sets.COLUMN_NAME)
    var name: String = "",
    @ColumnInfo(name = DBConstants.Sets.COLUMN_DESCRIPTION)
    var description: String = "",
    @ColumnInfo(name = DBConstants.Sets.COLUMN_IMAGE_URL)
    var imageUrl: String = "",
    @ColumnInfo(name = DBConstants.Sets.COLUMN_LANG)
    var lang: String = "",
    @ColumnInfo(name = DBConstants.Sets.COLUMN_NUM_OF_WORDS)
    var wordsCount: Int = 0,
    @ColumnInfo(name = DBConstants.Sets.COLUMN_STATUS)
    var status: Int = 0,
    @ColumnInfo(name = DBConstants.Sets.COLUMN_VISIBILITY)
    var visibitity: Int = 0,
    @ColumnInfo(name = DBConstants.Sets.COLUMN_THEME_CODES, index = true)
    var themeCodes: String = "",
)