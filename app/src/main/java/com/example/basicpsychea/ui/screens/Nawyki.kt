package com.example.basicpsychea.ui.screens


import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basicpsychea.R
import com.example.basicpsychea.data.NawykiData
import com.example.basicpsychea.data.nawyki_list
import com.example.basicpsychea.ui.AppViewModelProvider

@Composable
fun nawyki(modifier: Modifier = Modifier, viewModel: NawykiViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(
                stringResource(R.string.zdrowe_nawyki6),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
        }
        LazyColumn {
            items(nawyki_list) { nawykiItem ->
                BodyItemNa(exercises = nawykiItem, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun BodyItemNa(exercises: NawykiData, viewModel: NawykiViewModel) {
    var expanded by remember {
        mutableStateOf(viewModel.expandedStateMap[exercises.id] ?: false)
    }
    Card(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Button(
                onClick = {
                    expanded = !expanded
                    viewModel.expandedStateMap[exercises.id] = expanded
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary) // Use your preferred color here
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small))
                ) {
                    Text(
                        text = stringResource(exercises.title),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            if (expanded) {
                nawyki_Description(
                    nawyki_Description = exercises.description
                )
            }
        }
    }
}

@Composable
fun nawyki_Description(@StringRes nawyki_Description: Int) {
    Text(
        text = stringResource(nawyki_Description),
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(
            start = dimensionResource(R.dimen.padding_medium),
            top = dimensionResource(R.dimen.padding_small),
            end = dimensionResource(R.dimen.padding_medium),
            bottom = dimensionResource(R.dimen.padding_medium)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun nawykiPreview() {
    //nawyki()
}