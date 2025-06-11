package com.example.dictionary.ui.theme.Ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionary.ui.theme.DictionaryViewModel.DictionaryState
import com.example.dictionary.ui.theme.DictionaryViewModel.DictionaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(viewModel: DictionaryViewModel) {

    val state by viewModel.state.observeAsState()

    var query by remember { mutableStateOf("") }

    // Gradient background brush for the whole screen
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFa1c4fd), Color(0xFFc2e9fb))
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
            .padding(16.dp),
        color = Color.Transparent
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Dictionary App",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0d47a1)
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            TextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Enter a word") },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color(0xFF0d47a1),
                    cursorColor = Color(0xFF0d47a1),
                    focusedLabelColor = Color(0xFF0d47a1)

                ),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (query.isNotBlank()) {
                        viewModel.searchWord(query.trim())
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0d47a1),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Search",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            when (val result = state) {
                is DictionaryState.Loading -> {
                    CircularProgressIndicator(
                        color = Color(0xFF0d47a1),
                        strokeWidth = 4.dp,
                        modifier = Modifier.size(48.dp)
                    )
                }

                is DictionaryState.Success -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.9f)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Definition",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0d47a1)
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = result.definition,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = Color.DarkGray,
                                    lineHeight = 22.sp
                                )
                            )
                        }
                    }
                }

                is DictionaryState.Error -> {
                    Text(
                        text = "⚠️ ${result.message}",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                else -> {
                    Text(
                        text = "Enter a word and press Search.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF0d47a1)
                        )
                    )
                }
            }
        }
    }
}
