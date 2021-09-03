package xyz.volgoak.wordlearning.screens.dictionary

import DBConstants
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import xyz.volgoak.data.entities.Word
import xyz.volgoak.data.repository.WordsRepository

class DictionaryViewModel(
    private val wordsRepository: WordsRepository
) : ViewModel() {
    val words = wordsRepository.getDictionaryWords()
        .stateIn(viewModelScope, started = SharingStarted.Lazily, emptyList())

    fun resetWordProgress(word: Word) {

    }

    fun removeWordFromDictionary(word: Word) {
        viewModelScope.launch {
            wordsRepository.updateWord(
                word.copy(status = DBConstants.Words.OUT_OF_DICTIONARY)
            )
        }
    }
}