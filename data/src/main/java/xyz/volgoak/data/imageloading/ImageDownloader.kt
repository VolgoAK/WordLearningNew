package xyz.volgoak.data.imageloading

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import net.lingala.zip4j.core.ZipFile
import xyz.volgoak.data.StorageContract
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ImageDownloader(private val context: Context) {
    companion object {
        private const val PREFS_NAME = "ImageDownloaderPrefs"
        private const val KEY_IMAGES_LOADED = "images_loaded"

        fun isImageExist(context: Context, imageName: String): Boolean {
            val directory = File(context.filesDir, StorageContract.IMAGES_FOLDER)
            val image = File(directory, imageName)
            return image.exists()
        }
    }

    private val prefs by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    private var mImagesDir: File? = null

    fun downloadAllImagesIfRequired() {
        if (!prefs.getBoolean(KEY_IMAGES_LOADED, false)) {
            downloadAllImages()
            FirebaseAuth.getInstance().signInAnonymously().addOnSuccessListener {
                downloadAllImages()
            }
        }
    }

    private fun downloadAllImages() {
        checkOrCreateDirs()
        val photosFile = File(mImagesDir, StorageContract.PHOTOS_ARCHIVE)
        val imagesReference =
            FirebaseStorage.getInstance().getReference(StorageContract.IMAGES_FOLDER)
        val archiveReference = imagesReference.child(StorageContract.PHOTOS_ARCHIVE)
        archiveReference.getBytes(java.lang.Long.MAX_VALUE).addOnSuccessListener { bytes ->
            try {
                val fileOutputStream = FileOutputStream(photosFile)
                fileOutputStream.write(bytes)
                fileOutputStream.close()

                val zipFile = ZipFile(photosFile)
                zipFile.extractAll(mImagesDir!!.absolutePath)

                prefs.edit()
                    .putBoolean(KEY_IMAGES_LOADED, true)
                    .apply()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.addOnFailureListener {
            it.printStackTrace()
        }
    }

    private fun checkOrCreateDirs() {
        mImagesDir = File(context.filesDir, StorageContract.IMAGES_FOLDER)
        if (!mImagesDir!!.exists()) mImagesDir!!.mkdirs()
    }

    private fun downloadImage(imageName: String) {
        checkOrCreateDirs()
        val imageDirRef = FirebaseStorage.getInstance().getReference(StorageContract.IMAGES_FOLDER)
        val imageRef = imageDirRef.child(imageName)
        val imageFile = File(mImagesDir, imageName)
        imageRef.getBytes(java.lang.Long.MAX_VALUE).addOnSuccessListener { bytes ->
            try {
                val fous = FileOutputStream(imageFile)
                fous.write(bytes)
                fous.close()
            } catch (ex: FileNotFoundException) {
                ex.printStackTrace()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

    fun loadImage(imageName: String) {
        if (FirebaseAuth.getInstance().currentUser != null) {
            downloadImage(imageName)
        } else {
            FirebaseAuth.getInstance().signInAnonymously()
            FirebaseAuth.getInstance()
                .addAuthStateListener(object : FirebaseAuth.AuthStateListener {
                    override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
                        if (firebaseAuth.currentUser != null) {
                            downloadImage(imageName)
                            firebaseAuth.removeAuthStateListener(this)
                        }
                    }
                })
        }
    }
}
