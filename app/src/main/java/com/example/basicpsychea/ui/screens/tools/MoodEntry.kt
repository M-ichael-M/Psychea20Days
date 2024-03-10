package com.example.basicpsychea.ui.screens.tools

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.basicpsychea.ui.MoodViewModel

@Composable
fun MoodEntryScreen(moodViewModel: MoodViewModel
) {
    val moods = moodViewModel.getMoods().collectAsState(initial = emptyList())
}
