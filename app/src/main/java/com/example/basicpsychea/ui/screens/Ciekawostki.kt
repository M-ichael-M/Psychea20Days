package com.example.basicpsychea.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
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
import com.example.basicpsychea.R
import com.example.basicpsychea.data.CiewkawostkiData
import com.example.basicpsychea.data.ciekawostki_list

@Composable
fun Ciekawostki(    onNextButtonClicked: (Int) -> Unit,
                    modifier: Modifier = Modifier) {
    Column(modifier = Modifier) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(), // Rozciąga ramkę na całą dostępną szerokość

        ) {
            Text(
                "Strefa ciekawostek",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
        }
        LazyColumn {
            items(ciekawostki_list) {
                BodyItem(exercises = it, modifier = Modifier)
            }
        }
    }
}

@Composable
fun BodyItem(exercises: CiewkawostkiData, modifier: Modifier = Modifier) {
    var expanded by remember {
        mutableStateOf(false)
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
            Button(onClick = { expanded = !expanded },
                shape = CutCornerShape(10),
                modifier = Modifier
                    .fillMaxSize()
                    .height(150.dp)
                    .clip(MaterialTheme.shapes.medium)) {
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
                    Wiedza_image(
                        wiedzaImage = exercises.imageResourceId,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(100.dp)
                            .clip(MaterialTheme.shapes.medium))

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

        Ciekawostki({})
}