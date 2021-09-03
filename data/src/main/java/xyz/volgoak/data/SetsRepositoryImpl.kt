package xyz.volgoak.data

import kotlinx.coroutines.flow.Flow
import xyz.volgoak.data.dao.SetsDao
import xyz.volgoak.data.dao.WordDao
import xyz.volgoak.data.entities.WordsSet

class SetsRepositoryImpl(
    private val setsDao: SetsDao,
    private val wordsDao: WordDao
) : SetsRepository {
    override fun allSets(): Flow<List<WordsSet>> {
        return setsDao.allSetsFlow()
    }

    override fun setDetails(id: Long): Flow<WordsSet?> {
        return setsDao.getSetById(id)
    }

    override suspend fun addSetToDictionary(id: Long) {
        setsDao.updateSetStatus(id, DBConstants.Sets.IN_DICTIONARY)
        wordsDao.updateStatusForSetId(id, DBConstants.Words.IN_DICTIONARY)
    }

    override suspend fun removeSetFromDictionary(id: Long) {
        setsDao.updateSetStatus(id, DBConstants.Sets.OUT_OF_DICTIONARY)
        wordsDao.updateStatusForSetId(id, DBConstants.Words.OUT_OF_DICTIONARY)
    }
}
