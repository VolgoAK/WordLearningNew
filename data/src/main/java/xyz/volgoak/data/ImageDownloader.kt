package xyz.volgoak.data

import android.content.Context
import android.preference.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import net.lingala.zip4j.core.ZipFile
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ImageDownloader(var mContext: Context) {

    private var mImagesDir: File? = null

    fun downloadAllImages() {
        checkOrCreateDirs()
        val photosFile = File(mImagesDir, StorageContract.PHOTOS_ARCHIVE)
        val imagesReference = FirebaseStorage.getInstance().getReference("/images")
        val archiveReference = imagesReference.child(StorageContract.PHOTOS_ARCHIVE)
        FirebaseAuth.getInstance().signInAnonymously()
        archiveReference.getBytes(java.lang.Long.MAX_VALUE).addOnSuccessListener { bytes ->
            try {
                val fileOutputStream = FileOutputStream(photosFile)
                fileOutputStream.write(bytes)
                fileOutputStream.close()

                val zipFile = ZipFile(photosFile)
                zipFile.extractAll(mImagesDir!!.absolutePath)
                //todo move all constants to contract
                PreferenceManager.getDefaultSharedPreferences(mContext).edit()
                    .putBoolean("images_loaded", true).apply()


            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.addOnFailureListener {
            it.printStackTrace()
        }
    }

    private fun checkOrCreateDirs() {
        mImagesDir = File(mContext.filesDir, StorageContract.IMAGES_FOLDER)
        if (!mImagesDir!!.exists()) mImagesDir!!.mkdirs()
    }

    private fun downloadImage(imageName: String) {
        checkOrCreateDirs()
        val imageDirRef = FirebaseStorage.getInstance().getReference("/images")
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
            FirebaseAuth.getInstance().addAuthStateListener(object : FirebaseAuth.AuthStateListener {
                override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
                    if (firebaseAuth.currentUser != null) {
                        downloadImage(imageName)
                        firebaseAuth.removeAuthStateListener(this)
                    }
                }
            })
        }
    }

    companion object {

        val TAG = ImageDownloader::class.java.simpleName

        fun isImageExist(context: Context, imageName: String): Boolean {
            val directory = File(context.filesDir, StorageContract.IMAGES_FOLDER)
            val image = File(directory, imageName)
            return image.exists()
        }
    }
}
