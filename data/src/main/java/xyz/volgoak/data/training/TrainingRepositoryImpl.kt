package xyz.volgoak.data.training

import xyz.volgoak.data.dao.WordDao
import xyz.volgoak.data.entities.Word

class TrainingRepositoryImpl(
    private val wordsDao: WordDao
) : TrainingRepository {
    override suspend fun createTraining(type: TrainingType): Training {
        // todo sort words by trained
        // todo move words limit into preferences

        val wordLambda = { word: Word -> word.word }
        val translationLambda = { word: Word -> word.translation }

        val wordGetter = if (type == TrainingType.TRANSLATION_WORD) {
            translationLambda
        } else {
            wordLambda
        }
        val answerGetter = if (type == TrainingType.TRANSLATION_WORD) {
            wordLambda
        } else {
            translationLambda
        }

        val words = wordsDao.dictionaryWords()
            .take(10)
            .map { word ->
                val variants = wordsDao.getVariants(word.id, 3)
                    .map(answerGetter) + answerGetter(word)

                TrainingWord(
                    word = wordGetter(word),
                    answer = answerGetter(word),
                    variants = variants.shuffled(),
                    wordId = word.id
                )
            }

        return Training(words)
    }
}