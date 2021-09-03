package xyz.volgoak.data.repository

import kotlinx.coroutines.flow.Flow
import xyz.volgoak.data.dao.WordDao
import xyz.volgoak.data.entities.Word

class WordsRepositoryImpl(
    private val wordsDao: WordDao
) : WordsRepository {
    override fun getWordsBySetId(setId: Long): Flow<List<Word>> =
        wordsDao.getWordsBySetIdFlow(setId)

    override fun getDictionaryWords(): Flow<List<Word>> =
        wordsDao.dictionaryWordsFlow()

    override suspend fun updateWord(word: Word) {
        wordsDao.updateWords(word)
    }
}