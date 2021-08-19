package xyz.volgoak.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import xyz.volgoak.data.entities.WordsSet

@Dao
interface SetsDao {
    @Query("SELECT * FROM SETS_TABLE WHERE VISIBILITY=${DBConstants.Sets.VISIBLE}")
    suspend fun allSets(): List<WordsSet>

    @Query("SELECT * FROM SETS_TABLE")
    fun allSetsFlow(): Flow<List<WordsSet>>

    @Insert
    suspend fun insertSet(set: WordsSet): Long

    @Insert
    suspend fun insertSets(vararg sets: WordsSet)

    @Update
    suspend fun updateSets(vararg sets: WordsSet)

    @Query("SELECT * FROM SETS_TABLE WHERE _id = :setId")
    fun getSetById(setId: Long): Flow<WordsSet>

    @Query("SELECT * FROM SETS_TABLE WHERE THEME_CODES LIKE :themeCode")
    suspend fun getSetsByTheme(themeCode: String): List<WordsSet>
}
