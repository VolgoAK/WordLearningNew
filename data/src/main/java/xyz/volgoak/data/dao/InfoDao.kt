package xyz.volgoak.data.dao

import DBConstants
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.volgoak.data.entities.DictionaryInfo

@Dao
interface InfoDao {
    /*@Query(
        """
            SELECT COUNT(${DBConstants.BASE_ID}) AS ${DBConstants.Info.ALL_WORDS_COUNT},
                   COUNT(CASE WHEN ${DBConstants.Words.COLUMN_TRAINED_WT} >= $TRAINING_LIMIT
                        AND ${DBConstants.Words.COLUMN_TRAINED_TW} >= $TRAINING_LIMIT THEN 1 ELSE 0 END)
                        AS ${DBConstants.Info.STUDIED_WORDS_COUNT},
                   COUNT(CASE WHEN ${DBConstants.Words.COLUMN_STATUS} = ${DBConstants.Words.IN_DICTIONARY}
                        THEN 1 ELSE 0 END) AS ${DBConstants.Info.DICTIONARY_WORDS_COUNT}
                   FROM ${DBConstants.Words.TABLE_NAME}     
        """
    )
    fun dictionaryInfo(): Flow<DictionaryInfo>*/

    companion object {
        const val TRAINING_LIMIT = 4
    }
}
