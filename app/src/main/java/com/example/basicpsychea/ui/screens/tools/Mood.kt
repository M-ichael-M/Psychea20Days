package com.example.basicpsychea.ui.screens.tools

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MoodsScreen(viewModel: MoodViewModel) {
    val moods = viewModel.getMoods().collectAsState(initial = emptyList())
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        item {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                Text(text = moods.value.toString())
            }
        }
    }
}
