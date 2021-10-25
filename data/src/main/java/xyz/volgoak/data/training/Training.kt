package xyz.volgoak.data.training

class Training(
    private val words: List<TrainingWord>
) {
    private var currentIndex = 0
    var accessible = false
        private set

    val wordsCount: Int
        get() = words.size
    val answersCount: Int
        get() = if (accessible) currentIndex else currentIndex + 1

    fun firstWord(): TrainingWord? {
        currentIndex = 0
        return words.getOrNull(currentIndex)?.also {
            accessible = true
        }
    }

    fun nextWord(): TrainingWord? {
        currentIndex += 1
        return words.getOrNull(currentIndex)?.also {
            accessible = true
        }
    }

    fun checkAnswer(answer: String): Boolean {
        return if (accessible) {
            accessible = false
            words[currentIndex].checkAnswer(answer)
        } else {
            false
        }
    }
}