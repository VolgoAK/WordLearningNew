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

    suspend fun importDbFromAssets() {
        val asset = context.assets.open("words.json")
        val dictionaryJson = String(asset.readBytes())
        Timber.d("TESTING json is $dictionaryJson")
        val adapter = moshi.adapter(DictionaryNt::class.java)
        val dictionary = adapter.fromJson(dictionaryJson)!!

        val themes = dictionary.themes.map { it.toDb() }
        themeDao.insertThemes(themes)

        dictionary.sets.forEach { insertSet(it) }
    }

    private suspend fun insertSet(wordsSet: WordSetNt) {
        val setId = setsDao.insertSet(wordsSet.toDb())
        wordsSet.words.forEach {
            val wordId = wordsDao.insertWord(it.toDb())
            linksDao.insertLinks(Link(0, wordId, setId))
        }
    }
}