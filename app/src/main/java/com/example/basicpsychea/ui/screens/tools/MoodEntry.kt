package com.example.basicpsychea.ui.screens.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basicpsychea.data.mood.Mood
import com.example.basicpsychea.data.mood.MoodDatabase
import com.example.basicpsychea.data.mood.OfflineMoodRepository

@Composable
fun MoodEntryScreen(
) {
    val emotion = remember { mutableStateOf(0) }
    val date = remember { mutableStateOf(0.0) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = emotion.value.toString(),
            onValueChange = { emotion.value = it.toIntOrNull() ?: 0 },
            label = { Text("Emotion") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = date.value.toString(),
            onValueChange = { date.value = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Mood")
        }
    }
}
