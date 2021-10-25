package xyz.volgoak.wordlearning.screens.root

object Destinations {
    const val INITIALIZATION = "initialization"
    const val MAIN = "main"
    const val SETS = "sets"
    const val SET_ID_KEY = "set_id"
    const val SET_DETAILS = "set_details/$SET_ID_KEY"
    const val SET_DETAILS_FORMAT = "$SET_DETAILS/%d"
    const val SET_DETAILS_ROUTE = "$SET_DETAILS/{$SET_ID_KEY}"
    const val DICTIONARY = "dictionary"
    const val TRAINING = "training"
    const val TRAINING_RESULTS = "training_results"

    fun setDetails(id: Long) = SET_DETAILS_FORMAT.format(id)
}