package xyz.volgoak.data.repository

import kotlinx.coroutines.flow.Flow
import xyz.volgoak.data.entities.Word

interface WordsRepository {
    fun getWordsBySetId(setId: Long): Flow<List<Word>>
    fun getDictionaryWords(): Flow<List<Word>>
    suspend fun updateWord(word: Word)
}