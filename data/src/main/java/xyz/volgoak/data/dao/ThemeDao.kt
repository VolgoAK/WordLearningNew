package xyz.volgoak.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.volgoak.data.entities.Theme

@Dao
interface ThemeDao {
    @Query("SELECT * FROM THEMES_TABLE")
    fun allThemes(): Flow<List<Theme>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTheme(theme: Theme): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThemes(themes: List<Theme>)
}
