package com.example.basicpsychea.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicpsychea.R

@Composable
fun projekt(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }

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
                    stringResource(R.string.o_projekcie1),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
                Image(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(R.drawable.logo2),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                Text(
                    text = stringResource(R.string.psychea_20_dni_do_zdrowej_psychiki),
                    style = MaterialTheme.typography.headlineLarge
                        .copy(
                            color = Color.White,
                            shadow = Shadow(
                                color = Color.Gray,
                                offset = Offset(6f, 6f),
                                blurRadius = 4f
                            ),
                            textAlign = TextAlign.Center
                        ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp) // Odległość od krawędzi ekranu
                        .clip(RoundedCornerShape(8.dp)), // Zaokrąglenie rogów

                contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp)) // Zaokrąglenie rogów boxa
                            .clickable { showDialog = true }, // Obsługa kliknięcia
                        contentAlignment = Alignment.Center,
                        content = {
                            Box(
                                modifier = Modifier
                                    .width(180.dp) // Rozmiar tła
                                    .height(110.dp)
                                    .clip(RoundedCornerShape(8.dp)) // Zaokrąglenie rogów tła
                                    .background(Color.White), // Kolor tła
                                contentAlignment = Alignment.Center,
                                content = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.baseline_mail_outline_24),
                                            contentDescription = null,
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .size(50.dp), // Wielkość obrazka
                                        )
                                        Text(text = stringResource(R.string.wiadomo_od_tw_rc_w),
                                            textAlign = TextAlign.Center,
                                            fontFamily = FontFamily.Cursive)
                                    }
                                }
                            )
                        }
                    )
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text(stringResource(R.string.zako_czenie_projektu)) },
                        text = {
                            Column {
                                Text(stringResource(R.string.podziekowania_koncowe))
                                Text(text = stringResource(R.string.dzi_ki_e_byli_cie_z_nami))
                                Text(text = stringResource(R.string.for_better_psyche))
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(text = stringResource(R.string.kierownik_projektu_micha_ma_eczek))
                            }
                        },
                        confirmButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text(text = stringResource(id = R.string.ok))
                            }
                        }
                    )
                }
                Text(
                    text = stringResource(R.string.opis),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.opis_aplikacji),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.zwolnieni_z_teorii),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.o_zwolnionych),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Column {
                    ClickableFacebookProfileLink()
                    ClickableInstagramProfileLink()
                    ClickableTwitterProfileLink()
                    ClickableTiktokProfileLink()
                    ClickableYoutubeProfileLink()
                    ClickableRedditProfileLink()
                    ClickableMail()
                }
            }
        }
    }
}

@Composable
fun ClickableInstagramProfileLink() {
    val instagramProfileUrl = "https://www.instagram.com/psychea_20dni/"
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instagramProfileUrl))
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .height(40.dp),
            painter = painterResource(R.drawable.ig),
            contentDescription = null
        )
        Text(
            stringResource(R.string.instagram),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Composable
    fun ClickableFacebookProfileLink() {
    val facebookProfileUrl = "https://www.facebook.com/profile.php?id=61555384814923"
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUrl))
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .height(40.dp),
            painter = painterResource(R.drawable.fb),
            contentDescription = null
        )
        Text(
            stringResource(R.string.facebook),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ClickableTwitterProfileLink() {
    val facebookProfileUrl = "https://twitter.com/Psychea20"
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUrl))
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .height(40.dp),
            painter = painterResource(R.drawable.x),
            contentDescription = null
        )
        Text(
            stringResource(R.string.x),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ClickableMail() {
    val mail = "psychea075@gmail.com"
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$mail")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
            }
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .height(40.dp),
            painter = painterResource(R.drawable.baseline_email_24),
            contentDescription = null
        )
        Text(
            stringResource(R.string.mail),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ClickableTiktokProfileLink() {
    val facebookProfileUrl = "https://www.tiktok.com/@psychea20"
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUrl))
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .height(40.dp),
            painter = painterResource(R.drawable.tiktok),
            contentDescription = null
        )
        Text(
            stringResource(R.string.tiktok),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ClickableYoutubeProfileLink() {
    val facebookProfileUrl = "https://www.youtube.com/@Psychea20"
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUrl))
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .height(40.dp),
            painter = painterResource(R.drawable.yt),
            contentDescription = null
        )
        Text(
            stringResource(R.string.youtube),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ClickableRedditProfileLink() {
    val facebookProfileUrl = "https://www.reddit.com/user/Successful_Disk8541/"
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUrl))
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .height(40.dp),
            painter = painterResource(R.drawable.reddit),
            contentDescription = null
        )
        Text(
            stringResource(R.string.reddit),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProjektPreview() {
    projekt()
}