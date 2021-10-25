package xyz.volgoak.wordlearning.screens.training

import xyz.volgoak.data.training.TrainingWord
import xyz.volgoak.datacore.Event

data class TrainingState(
    val currentWord: TrainingWord? = null,
    val answered: Boolean = false,
    val answeredWords: Int = 0,
    val wordsCount: Int = 0,
    val latestAnswerIndex: Int = -1,
    val correct: Boolean = false,
    val openResultsEvent: Event<Unit>? = null,
) {
    fun applyEvent(event: TrainingEvent): TrainingState = when (event) {
        is TrainingEvent.TrainingLoaded -> copy(
            wordsCount = event.wordsCount
        )
        is TrainingEvent.WordChanged -> copy(
            currentWord = event.word,
            answered = false
        )
        is TrainingEvent.Answered -> copy(
            answered = true,
            answeredWords = event.index,
            latestAnswerIndex = event.variantIndex,
            correct = event.correct
        )
        is TrainingEvent.TrainingFinished -> copy(
            openResultsEvent = Event(Unit)
        )
    }
}

sealed class TrainingEvent {
    data class TrainingLoaded(
        val wordsCount: Int
    ) : TrainingEvent()

    data class WordChanged(val word: TrainingWord) : TrainingEvent()
    data class Answered(
        val index: Int,
        val variantIndex: Int,
        val correct: Boolean
    ) : TrainingEvent()

    object TrainingFinished: TrainingEvent()
}