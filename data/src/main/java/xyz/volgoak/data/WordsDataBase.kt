package xyz.volgoak.data

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.volgoak.data.dao.*
import xyz.volgoak.data.entities.Link
import xyz.volgoak.data.entities.Theme
import xyz.volgoak.data.entities.Word
import xyz.volgoak.data.entities.WordsSet

@Database(
    entities = [
        Word::class,
        WordsSet::class,
        Link::class,
        Theme::class
    ],
    version = DBConstants.DB_VERSION,
    exportSchema = false
)
abstract class WordsDataBase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun setsDao(): SetsDao
    abstract fun linkDao(): LinkDao
    abstract fun themeDao(): ThemeDao
    abstract fun infoDao(): InfoDao
}
