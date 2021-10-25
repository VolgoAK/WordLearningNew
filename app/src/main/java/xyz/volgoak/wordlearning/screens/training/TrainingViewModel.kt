package xyz.volgoak.wordlearning.screens.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.volgoak.data.training.Training
import xyz.volgoak.data.training.TrainingRepository
import xyz.volgoak.data.training.TrainingType

class TrainingViewModel(
    private val trainingRepository: TrainingRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TrainingState())
    val state = _state.asStateFlow()

    private lateinit var training: Training

    init {
        loadTraining()
    }

    fun checkAnswer(answer: String, index: Int) {
        if(training.accessible) {
            val correct = training.checkAnswer(answer)
            proceedEvent(
                TrainingEvent.Answered(
                    training.answersCount,
                    index,
                    correct
                )
            )
        }
    }

    fun nextWord() {
        training.nextWord()?.let {
            proceedEvent(TrainingEvent.WordChanged(it))
        } ?: run {
           proceedEvent(TrainingEvent.TrainingFinished)
        }
    }

    private fun loadTraining() {
        viewModelScope.launch {
            training = trainingRepository.createTraining(TrainingType.TRANSLATION_WORD)
            proceedEvent(TrainingEvent.TrainingLoaded(training.wordsCount))
            proceedEvent(TrainingEvent.WordChanged(training.firstWord()!!))
        }
    }

    private fun proceedEvent(event: TrainingEvent) {
        _state.value = _state.value.applyEvent(event)
    }
}