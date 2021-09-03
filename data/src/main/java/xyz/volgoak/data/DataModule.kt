package xyz.volgoak.data

import androidx.room.Room
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import xyz.volgoak.data.dbimport.DbImporter
import xyz.volgoak.data.imageloading.ImageDownloader
import xyz.volgoak.data.repository.WordsRepository
import xyz.volgoak.data.repository.WordsRepositoryImpl
import xyz.volgoak.datacore.BuildConfig

val dataModule = module {

    single {
        Room.databaseBuilder(
            get(),
            WordsDataBase::class.java,
            "words.db"
        )
            //.createFromAsset("words_database.db")
            .apply {
                if (BuildConfig.DEBUG) {
                    //fallbackToDestructiveMigration()
                }
            }.build()
    }

    single { get<WordsDataBase>().setsDao() }
    single { get<WordsDataBase>().themeDao() }
    single { get<WordsDataBase>().linkDao() }
    single { get<WordsDataBase>().infoDao() }
    single { get<WordsDataBase>().wordDao() }

    single<SetsRepository> {
        SetsRepositoryImpl(
            setsDao = get(),
            wordsDao = get()
        )
    }
    single<WordsRepository> {
        WordsRepositoryImpl(wordsDao = get())
    }

    single {
        DbImporter(
            context = get(),
            moshi = get(),
            themeDao = get(),
            setsDao = get(),
            linksDao = get(),
            wordsDao = get()
        )
    }

    single { Moshi.Builder().build() }

    single { ImageDownloader(get()) }
}