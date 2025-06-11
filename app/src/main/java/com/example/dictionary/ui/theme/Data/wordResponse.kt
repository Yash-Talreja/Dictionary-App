package com.example.dictionary.ui.theme.Data

// WordResponse.kt
data class WordResponse(
    val word: String,
    val meanings: List<Meaning>
)

data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>
)

data class Definition(
    val definition: String,
    val example: String? = null,
    val synonyms: List<String>? = null,
    val antonyms: List<String>? = null
)
