package xyz.volgoak.data

import kotlinx.coroutines.flow.Flow
import xyz.volgoak.data.entities.WordsSet

interface SetsRepository {
    fun allSets(): Flow<List<WordsSet>>
    fun setDetails(id: Long): Flow<WordsSet?>
    suspend fun addSetToDictionary(id: Long)
    suspend fun removeSetFromDictionary(id: Long)
}