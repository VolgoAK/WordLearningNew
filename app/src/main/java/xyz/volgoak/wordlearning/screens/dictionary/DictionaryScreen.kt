package xyz.volgoak.wordlearning.screens.dictionary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import timber.log.Timber
import xyz.volgoak.data.entities.Word
import xyz.volgoak.wordlearning.R
import xyz.volgoak.wordlearning.elements.WordsScaffold

@Composable
fun DictionaryScreen(navController: NavController) {
    val viewModel = getViewModel<DictionaryViewModel>()
    val words = viewModel.words.collectAsState()
    WordsScaffold(
        title = stringResource(R.string.main_dictionary),
        onNavigationClick = { navController.popBackStack() }
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