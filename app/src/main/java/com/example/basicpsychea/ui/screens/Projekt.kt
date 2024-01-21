package com.example.basicpsychea.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.basicpsychea.R
import com.example.basicpsychea.data.nawyki_list

@Composable
fun projekt(onNextButtonClicked: (Int) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    "O projekcie",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
                Image(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium),
                    painter = painterResource(R.drawable.ikona),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                Text(
                    text = "PSYCHEA - 20 dni do zdrowej psychiki",
                    style = MaterialTheme.typography.headlineLarge
                        .copy(
                            color = Color.White,
                            shadow = Shadow(
                                color = Color.Gray,
                                offset = Offset(6f, 6f),
                                blurRadius = 4f
                            ),
                            textAlign = TextAlign.Center
                        )
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                Text(
                    text = "Opis:",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Tu jest opis",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Zwolnieni z teorii",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "O zwolnionych z teorii",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier.padding(10.dp)
                ) {
                    Row(
                        modifier = modifier
                    ) {
                        Image(
                            modifier = Modifier
                                .height(40.dp),
                            painter = painterResource(R.drawable.ig),
                            contentDescription = null
                        )
                        ClickableInstagramProfileLink()
                    }
                }
            }
        }
    }
}

@Composable
fun ClickableInstagramProfileLink() {
    val instagramProfileUrl = "https://www.instagram.com/psychea_20dni/"
    val context = LocalContext.current

    ClickableText(
        text = buildAnnotatedString {
            append("Instagram: @psychea_20dni")
        },
        onClick = { offset ->
            if (offset in 19..24) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instagramProfileUrl))
                context.startActivity(intent)
            }
        },
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ProjektPreview() {
    projekt({})
}