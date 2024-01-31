package com.example.basicpsychea.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicpsychea.R
import com.example.basicpsychea.data.myData
import com.example.basicpsychea.data.my_list

@Composable
fun my(modifier: Modifier = Modifier) {
    Column(modifier = Modifier) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(
                stringResource(R.string.o_nas_),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
        }
        LazyColumn {
            items(my_list) { myItem ->
                BodyItemMy(exercises = myItem, modifier = Modifier)
            }
        }
    }
}

@Composable
fun BodyItemMy(exercises: myData, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)) ) {
        Text(text = stringResource(
    exercises.title),
            modifier
                .fillMaxSize()
                .padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun myPreview() {
    my()
}
