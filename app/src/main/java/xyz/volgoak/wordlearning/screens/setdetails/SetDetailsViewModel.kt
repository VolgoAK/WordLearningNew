package xyz.volgoak.wordlearning.screens.setdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import xyz.volgoak.data.SetsRepository
import xyz.volgoak.data.repository.WordsRepository

class SetDetailsViewModel(
    private val setId: Long,
    private val setsRepository: SetsRepository,
    private val wordsRepository: WordsRepository,
) : ViewModel() {
    val setDetails = setsRepository.setDetails(setId)
        .stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = null)

    val words = wordsRepository.getWordsBySetId(setId)
        .shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)

    fun onChangeStatusClicked() {
        viewModelScope.launch {
            setDetails.value?.status?.let { status ->
                if(status == DBConstants.Sets.IN_DICTIONARY) {
                    setsRepository.removeSetFromDictionary(setId)
                } else {
                    setsRepository.addSetToDictionary(setId)
                }
            }
        }
    }
}