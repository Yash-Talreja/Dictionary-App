package com.example.dictionary.ui.theme.Data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: DictionaryApi by lazy {
        retrofit.create(DictionaryApi::class.java)

    }


}