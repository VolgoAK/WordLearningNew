package xyz.volgoak.wordlearning.screens.sets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import xyz.volgoak.data.SetsRepository

class SetsViewModel(
    private val setsRepository: SetsRepository
): ViewModel() {
    val sets = setsRepository.allSets()
        .stateIn(viewModelScope, started = SharingStarted.Lazily, emptyList())
}