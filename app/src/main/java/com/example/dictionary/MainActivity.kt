package com.example.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.ui.theme.Ui.DictionaryScreen
import com.example.dictionary.ui.theme.DictionaryTheme
import com.example.dictionary.ui.theme.DictionaryViewModel.DictionaryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dictionaryviewModel = ViewModelProvider(this)[DictionaryViewModel::class.java]
        setContent {
            DictionaryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                       DictionaryScreen(dictionaryviewModel)
                    }
                }
            }
        }
    }
}
