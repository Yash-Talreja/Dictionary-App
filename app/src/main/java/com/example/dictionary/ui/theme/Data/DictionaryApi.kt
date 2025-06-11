package com.example.dictionary.ui.theme.Data

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(@Path("word") word: String)
    : retrofit2.Response<List<WordResponse>>

}