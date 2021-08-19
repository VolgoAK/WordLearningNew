import android.provider.BaseColumns

object DBConstants {
    const val DB_NAME = "NEW_WORDS_DATABASE"
    const val DB_VERSION = 16
    const val DICTIONARY_ID: Long = -3

    const val BASE_ID = "_id"

    object Words : BaseColumns {
        const val OUT_OF_DICTIONARY = 0
        const val IN_DICTIONARY = 1
        const val TABLE_NAME = "WORDS_TABLE"
        const val COLUMN_WORD = "WORD"
        const val COLUMN_TRANSLATION = "TRANSLATION"
        const val COLUMN_TRANSCRIPTION = "TRANSCRIPTION"
        const val COLUMN_TRAINED_WT = "WT_TRAINED"
        const val COLUMN_TRAINED_TW = "TW_TRAINED"
        const val COLUMN_STUDIED = "STUDIED"
        const val COLUMN_STATUS = "STATUS"
        const val COLUMN_ADDED_TIME = "ADDED_TIME"
        const val COLUMN_LAST_TRAINED_TIME = "LAST_TRAINED_TIME"
    }

    object Sets : BaseColumns {
        const val OUT_OF_DICTIONARY = 0
        const val IN_DICTIONARY = 1
        const val INVISIBLE = 0
        const val VISIBLE = 1
        const val TABLE_NAME = "SETS_TABLE"
        const val COLUMN_NAME = "NAME"
        const val COLUMN_NUM_OF_WORDS = "WORDS_COUNT"
        const val COLUMN_STATUS = "STATUS"
        const val COLUMN_VISIBILITY = "VISIBILITY"
        const val COLUMN_DESCRIPTION = "DESCRIPTION"
        const val COLUMN_IMAGE_URL = "image_url"
        const val COLUMN_LANG = "LANG"
        const val COLUMN_THEME_CODES = "THEME_CODES"
    }

    object Themes : BaseColumns {
        const val THEME_ANY = ""
        const val TABLE_NAME = "THEMES_TABLE"
        const val COLUMN_NAME = "THEME_NAME"
        const val COLUMN_CODE = "THEME_COD"
    }

    object WordLinks : BaseColumns {
        const val TABLE_NAME = "WORD_IDS_TABLE"
        const val COLUMN_SET_ID = "SET_ID"
        const val COLUMN_WORD_ID = "WORD_ID"
    }

    object Info {
        const val ALL_WORDS_COUNT = "all_words"
        const val DICTIONARY_WORDS_COUNT = "dictionary_words"
        const val STUDIED_WORDS_COUNT = "studied_words"
    }
}