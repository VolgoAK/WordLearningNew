package xyz.volgoak.data.dbimport

import android.content.Context
import com.squareup.moshi.Moshi
import timber.log.Timber
import xyz.volgoak.data.dao.LinkDao
import xyz.volgoak.data.dao.SetsDao
import xyz.volgoak.data.dao.ThemeDao
import xyz.volgoak.data.dao.WordDao
import xyz.volgoak.data.dbimport.model.DictionaryNt
import xyz.volgoak.data.dbimport.model.WordSetNt
import xyz.volgoak.data.dbimport.model.toDb
import xyz.volgoak.data.entities.Link
import kotlin.reflect.typeOf

class DbImporter(
    private val context: Context,
    private val moshi: Moshi,
    private val themeDao: ThemeDao,
    private val setsDao: SetsDao,
    private val linksDao: LinkDao,
    private val wordsDao: WordDao
    ) {
    companion object {
        private const val PREFS_NAME = "DbImporterPrefs"
        private const val KEY_DB_IMPORTED = "db_imported"
    }

    private val prefs by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    suspend fun importDbFromAssetsIfRequired() {
        if(!prefs.getBoolean(KEY_DB_IMPORTED, false)) {
            importDbFromAssets()
        }
    }

    private suspend fun importDbFromAssets() {
        val asset = context.assets.open("words.json")
        val dictionaryJson = String(asset.readBytes())
        val adapter = moshi.adapter(DictionaryNt::class.java)
        val dictionary = adapter.fromJson(dictionaryJson)!!

        val themes = dictionary.themes.map { it.toDb() }
        themeDao.insertThemes(themes)

        dictionary.sets.forEach { insertSet(it) }
        prefs.edit()
            .putBoolean(KEY_DB_IMPORTED, true)
            .apply()
    }

    private suspend fun insertSet(wordsSet: WordSetNt) {
        val setId = setsDao.insertSet(wordsSet.toDb())
        wordsSet.words.forEach {
            val wordId = wordsDao.insertWord(it.toDb())
            linksDao.insertLinks(Link(0, wordId, setId))
        }
    }
}