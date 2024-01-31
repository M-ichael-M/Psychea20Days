package com.example.basicpsychea.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basicpsychea.R
import com.example.basicpsychea.data.CiewkawostkiData
import com.example.basicpsychea.data.ciekawostki_list

@Composable
fun Ciekawostki(modifier: Modifier = Modifier, viewModel: CiekawostkiViewModel) {
    LazyColumn(modifier = modifier) {
        item {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    "Strefa ciekawostek",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }

        items(ciekawostki_list) { exercises ->
            BodyItem(exercises = exercises, modifier = Modifier, viewModel = viewModel)
        }
    }
}

@Composable
fun BodyItem(exercises: CiewkawostkiData, modifier: Modifier = Modifier, viewModel: CiekawostkiViewModel) {
    var expanded by remember {
        mutableStateOf(viewModel.expandedStateMap[exercises.id] ?: false)
    }
    Card(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clip(RoundedCornerShape(8.dp)) // Adjust the radius as needed for the background
    ) {
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
                    .fillMaxWidth()
                    .heightIn(min = 150.dp) // Set a minimum height for the button
                    .clip(RoundedCornerShape(8.dp)) // Adjust the radius as needed for the button
                    .background(MaterialTheme.colorScheme.primary) // Use the primary color for the button background
            ) {
                Text(
                    text = stringResource(exercises.title),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
            if (expanded) {
                Ciekawostki_Description(
                    ciekawostki_Description = exercises.description,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Ciekawostki_Image(
                        ciekawostkiImage = exercises.imageResourceId,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(100.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
            }
        }
    }
}




@Composable
fun Ciekawostki_Image(
    @DrawableRes ciekawostkiImage: Int,
    modifier: Modifier = Modifier
) {
    Image(modifier = modifier
        .clip(MaterialTheme.shapes.medium),

        painter = painterResource(ciekawostkiImage),
        contentDescription = null)
}

@Composable
fun Ciekawostki_Description(@StringRes ciekawostki_Description: Int, modifier: Modifier = Modifier)
{
    Text(text = stringResource(ciekawostki_Description), style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(
        start = dimensionResource(R.dimen.padding_medium),
        top = dimensionResource(R.dimen.padding_small),
        end = dimensionResource(R.dimen.padding_medium),
        bottom = dimensionResource(R.dimen.padding_medium)))
}

@Preview(showBackground = true)
@Composable
fun CiekawostkiPreview() {

        //Ciekawostki()
}