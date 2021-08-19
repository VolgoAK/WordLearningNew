package xyz.volgoak.data.entities

import DBConstants
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = DBConstants.Themes.TABLE_NAME,
    indices = [Index(value = arrayOf(DBConstants.Themes.COLUMN_CODE), unique = true)]
)
data class Theme(
    @ColumnInfo(name = DBConstants.BASE_ID)
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = DBConstants.Themes.COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = DBConstants.Themes.COLUMN_CODE)
    val code: String,
)