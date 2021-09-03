package xyz.volgoak.wordlearning.screens.setdetails

import DBConstants
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import xyz.volgoak.data.StorageContract
import xyz.volgoak.data.entities.Word
import xyz.volgoak.data.entities.WordsSet
import java.io.File

@Composable
fun SetDetailsScreen(
    navController: NavController,
    setId: Long
) {
    val viewModel = getViewModel<SetDetailsViewModel> {
        parametersOf(setId)
    }
    val setDetails = viewModel.setDetails
        .collectAsState(null)
    val words = viewModel.words
        .collectAsState(emptyList())

    LazyColumn {
        setDetails.value?.let {
            item {
                SetDetailsWidget(set = it)
            }
        }
        items(words.value) { item ->
            WordItem(word = item)
        }
    }

    setDetails.value?.let { wordSet ->
        FloatingActionButton(
            onClick = {
                viewModel.onChangeStatusClicked()
            }
        ) {
            val icon = if (wordSet.status == DBConstants.Sets.IN_DICTIONARY) {
                Icons.Filled.Delete
            } else {
                Icons.Filled.Add
            }
            Icon(icon, contentDescription = null)
        }
    }
}

@Composable
fun SetDetailsWidget(set: WordsSet) {
    val imageUrl = set.imageUrl
    val imagesDir = File(LocalContext.current.filesDir, StorageContract.IMAGES_FOLDER)
    val imageFile = File(imagesDir, imageUrl)
    val imageUri = Uri.fromFile(imageFile)

    Image(
        painter = rememberImagePainter(imageUri),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    )
}

@Composable
fun WordItem(word: Word) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        Text(
            text = word.word,
            style = MaterialTheme.typography.h5
        )
        Text(
            text = word.translation,
            style = MaterialTheme.typography.subtitle1
        )
        Divider(
            modifier = Modifier.padding(
                top = 8.dp
            ),
            color = MaterialTheme.colors.onPrimary
        )
    }
}