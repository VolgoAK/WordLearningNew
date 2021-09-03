package xyz.volgoak.wordlearning.screens.initialization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.volgoak.data.dbimport.DbImporter
import xyz.volgoak.datacore.Event
import xyz.volgoak.wordlearning.screens.root.Destinations

class InitializationViewModel(
    private val dbImporter: DbImporter
): ViewModel() {
    val navigationFlow = MutableSharedFlow<Event<String>>()

    init {
        importDbIfRequired()
    }

    private fun importDbIfRequired() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dbImporter.importDbFromAssetsIfRequired()
            }
            navigationFlow.emit(Event(Destinations.MAIN))
        }
    }
}