package com.example.dictionary.ui.theme.DictionaryViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary.ui.theme.Data.RetrofitInstance
import com.example.dictionary.ui.theme.Data.WordResponse
import kotlinx.coroutines.launch
import kotlin.collections.firstOrNull


sealed class DictionaryState {
    object Loading : DictionaryState()
    data class Success(val definition: String) : DictionaryState()
    data class Error(val message: String) : DictionaryState()
}

class DictionaryViewModel : ViewModel() {

    private val dictionaryApi = RetrofitInstance.api

    private val _state = MutableLiveData<DictionaryState>()
    val state: MutableLiveData<DictionaryState> = _state

    fun searchWord(word: String) {
        _state.value = DictionaryState.Loading

        viewModelScope.launch {
            try {
                val response = dictionaryApi.getWordInfo(word)

                if (response.isSuccessful) {
                    val wordResponse: WordResponse? = response.body()?.firstOrNull()
                    val definition = wordResponse
                        ?.meanings?.firstOrNull()
                        ?.definitions?.firstOrNull()
                        ?.definition

                    if (!definition.isNullOrEmpty()) {
                        _state.value = DictionaryState.Success(definition)
                    } else {
                        _state.value = DictionaryState.Error("No definition found.")
                    }
                } else {
                    _state.value = DictionaryState.Error("Invalid response.")
                }
            } catch (e: Exception) {
                _state.value = DictionaryState.Error("Error: ${e.message}")
            }
        }

    }
}
