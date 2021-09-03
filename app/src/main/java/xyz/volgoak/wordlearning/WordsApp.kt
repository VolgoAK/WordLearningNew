package xyz.volgoak.wordlearning

import android.app.Application
import com.google.firebase.FirebaseApp
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import xyz.volgoak.data.imageloading.ImageDownloader
import xyz.volgoak.data.dataModules
import xyz.volgoak.wordlearning.di.appModule
import xyz.volgoak.wordlearning.di.viewModelsModule

class WordsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(applicationContext)
            modules(
                appModule,
                viewModelsModule,
                *dataModules
            )
        }

        get<ImageDownloader>()
            .downloadAllImagesIfRequired()
    }
}