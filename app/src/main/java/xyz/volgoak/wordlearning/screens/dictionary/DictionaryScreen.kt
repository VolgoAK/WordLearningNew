package xyz.volgoak.wordlearning.screens.dictionary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import timber.log.Timber
import xyz.volgoak.data.entities.Word
import xyz.volgoak.wordlearning.R

@Composable
fun DictionaryScreen(navController: NavController) {
    val viewModel = getViewModel<DictionaryViewModel>()
    val words = viewModel.words.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.main_dictionary))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.core_back),
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                },
            )
        }
    ) {
        LazyColumn {
            items(words.value) { item ->
                WordItem(word = item) {
                    Timber.d("Word clicked $it")
                }
            }
        }
    }
}

@Composable
fun WordItem(word: Word, onWordClicked: (Word) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onWordClicked(word) }
    ) {
        Text(text = word.word)
    }
}