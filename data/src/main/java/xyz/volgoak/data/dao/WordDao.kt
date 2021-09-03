package xyz.volgoak.data.dao

import DBConstants
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import xyz.volgoak.data.entities.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM words_table WHERE STATUS=${DBConstants.Words.IN_DICTIONARY}")
    suspend fun dictionaryWords(): List<Word>

    @Query("SELECT * FROM words_table WHERE STATUS=${DBConstants.Words.IN_DICTIONARY}")
    fun dictionaryWordsFlow(): Flow<List<Word>>

    @Query("SELECT * FROM words_table WHERE STATUS=${DBConstants.Words.IN_DICTIONARY}")
    fun dictionaryWordsFlowableLD(): Flow<List<Word>>

    @Insert
    suspend fun insertWord(word: Word): Long

    @Insert
    suspend fun insertWords(vararg words: Word)

    @Update
    suspend fun updateWords(vararg words: Word)

    @Update
    suspend fun updateWords(words: List<Word>)

    @Query("SELECT * FROM words_table WHERE _id = :id")
    suspend fun getWordById(id: Long): Word

    @Query("SELECT * FROM WORDS_TABLE WHERE WORD = :word AND TRANSLATION = :translation")
    suspend fun getWord(word: String, translation: String): Word

    @Query(
        """
            SELECT * FROM words_table
            WHERE _id != :id AND STATUS = ${DBConstants.Words.IN_DICTIONARY}
            ORDER BY RANDOM() LIMIT :wordsLimit"""
    )
    suspend fun getVariants(id: Long, wordsLimit: Int): List<Word>

    @Query(
        """
            SELECT * FROM words_table
            WHERE _id != :id AND _id IN
                (SELECT ${DBConstants.WordLinks.COLUMN_WORD_ID} FROM ${DBConstants.WordLinks.TABLE_NAME}
                WHERE ${DBConstants.WordLinks.COLUMN_SET_ID}=:setId)
            ORDER BY RANDOM() LIMIT :wordsLimit"""
    )
    suspend fun getVariants(id: Long, wordsLimit: Int, setId: Long): List<Word>

    @Query(
        """
            SELECT * FROM words_table 
            WHERE _id IN
                (SELECT ${DBConstants.WordLinks.COLUMN_WORD_ID} FROM ${DBConstants.WordLinks.TABLE_NAME}
                WHERE ${DBConstants.WordLinks.COLUMN_SET_ID}=:setId)
            ORDER BY WORD COLLATE NOCASE"""
    )
    suspend fun getWordsBySetId(setId: Long): List<Word>

    @Query(
        """
            SELECT * FROM words_table 
            WHERE _id IN
                (SELECT ${DBConstants.WordLinks.COLUMN_WORD_ID} FROM ${DBConstants.WordLinks.TABLE_NAME}
                WHERE ${DBConstants.WordLinks.COLUMN_SET_ID}=:setId)"""
    )
    fun getWordsBySetIdFlow(setId: Long): Flow<MutableList<Word>>

    @Query(
        """
        UPDATE words_table
        SET STATUS=:status
        WHERE _id IN
            (SELECT ${DBConstants.WordLinks.COLUMN_WORD_ID} FROM ${DBConstants.WordLinks.TABLE_NAME}
                WHERE ${DBConstants.WordLinks.COLUMN_SET_ID}=:setId)"""
    )
    suspend fun updateStatusForSetId(setId: Long, status: Int)
}