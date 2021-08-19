package xyz.volgoak.data

import kotlinx.coroutines.flow.Flow
import xyz.volgoak.data.dao.SetsDao
import xyz.volgoak.data.entities.WordsSet

class SetsRepositoryImpl(
    private val setsDao: SetsDao
): SetsRepository {
    override fun allSets(): Flow<List<WordsSet>> {
        return setsDao.allSetsFlow()
    }
}
