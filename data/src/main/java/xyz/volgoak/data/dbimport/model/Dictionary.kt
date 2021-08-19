package xyz.volgoak.data.dbimport.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThemeNt(
    val code: String,
    val name: String
)

@JsonClass(generateAdapter = true)
data class WordSetNt(
    val name: String,
    val description: String,
    val themeCodes: String,
    val imageUrl: String,
    val words: List<WordNt>
)

@JsonClass(generateAdapter = true)
data class WordNt(
    val translation: String,
    val word: String,
    val transcription: String?
)

@JsonClass(generateAdapter = true)
data class DictionaryNt(
    val themes: List<ThemeNt>,
    val sets: List<WordSetNt>
)
