package xyz.volgoak.data

import kotlinx.coroutines.flow.Flow
import xyz.volgoak.data.entities.WordsSet

interface SetsRepository {
    fun allSets(): Flow<List<WordsSet>>
}