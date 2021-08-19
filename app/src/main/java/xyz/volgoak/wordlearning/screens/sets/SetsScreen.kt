package xyz.volgoak.wordlearning.screens.sets

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import org.koin.androidx.compose.getViewModel
import xyz.volgoak.data.StorageContract
import xyz.volgoak.data.entities.WordsSet
import java.io.File

@Composable
fun SetsScreen(navController: NavController) {
    val viewModel = getViewModel<SetsViewModel>()
    val sets = viewModel.sets.collectAsState()
    LazyColumn {
        items(sets.value) { item ->
            SetItem(set = item)
        }
    }
}

@Composable
fun SetItem(set: WordsSet) {

    val imageUrl = set.imageUrl
    val imagesDir = File(LocalContext.current.filesDir, StorageContract.IMAGES_FOLDER)
    val imageFile = File(imagesDir, imageUrl)
    val imageUri = Uri.fromFile(imageFile)
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.weight(1f)) {
            Text(text = set.name)
        }
        Image(
            painter = rememberImagePainter(imageUri),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(2.dp, Color.Gray, CircleShape)
        )
    }
}