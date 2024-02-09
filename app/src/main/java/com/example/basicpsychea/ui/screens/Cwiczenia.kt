package com.example.basicpsychea.ui.screens

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.basicpsychea.R
import com.example.basicpsychea.data.CwiczeniaData
import com.example.basicpsychea.data.cwiczenia_list

@Composable
fun Cwiczenia(viewModel: CwiczeniaViewModel) {
    Column(modifier = Modifier) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(
                stringResource(R.string.strefa_wiczen),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
        }
        LazyColumn {
            items(cwiczenia_list) { cwiczeniaItem ->
                BodyItemCw(exercises = cwiczeniaItem, modifier = Modifier, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun BodyItemCw(exercises: CwiczeniaData, modifier: Modifier = Modifier, viewModel: CwiczeniaViewModel) {
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
                    .height(150.dp)
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
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            }
            if (expanded) {
                cwiczenia_Description(
                    cwiczenia_Description = exercises.description,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )

            }
        }
    }
}

@Composable
fun cwiczenia_Description(@StringRes cwiczenia_Description: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(cwiczenia_Description),
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
fun CwiczeniaPreview() {
    //Cwiczenia()
}
