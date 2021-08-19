package xyz.volgoak.data.entities

import DBConstants
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = DBConstants.WordLinks.TABLE_NAME, foreignKeys = [
        ForeignKey(
            entity = WordsSet::class, parentColumns = arrayOf(DBConstants.BASE_ID),
            childColumns = arrayOf(DBConstants.WordLinks.COLUMN_SET_ID)
        ),
        ForeignKey(
            entity = Word::class, parentColumns = arrayOf(DBConstants.BASE_ID),
            childColumns = arrayOf(DBConstants.WordLinks.COLUMN_WORD_ID)
        )
    ]
)
data class Link(
    @ColumnInfo(name = DBConstants.BASE_ID)
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = DBConstants.WordLinks.COLUMN_WORD_ID, index = true)
    val wordId: Long,
    @ColumnInfo(name = DBConstants.WordLinks.COLUMN_SET_ID, index = true)
    val idOfSet: Long,
)