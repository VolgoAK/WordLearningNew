package xyz.volgoak.data.training

class TrainingWord(
    val word: String,
    val answer: String,
    val variants: List<String>,
    val wordId: Long
) {
    var answered = false
        private set
    var correct = false
        private set

    fun checkAnswer(answer: String): Boolean {
        answered = true
        correct = answer == this.answer
        return correct
    }
}