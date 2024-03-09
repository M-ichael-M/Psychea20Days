package com.example.basicpsychea.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.example.basicpsychea.R
import com.example.basicpsychea.data.quotes_list
import com.example.basicpsychea.ui.PsycheaScreen
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@SuppressLint("PrivateResource")
@Composable
fun HomeScreen(
    screens: List<PsycheaScreen>,
    onNextButtonClicked: (PsycheaScreen) -> Unit,
    modifier: Modifier = Modifier
) {

    val installDate = getInstallDate()
    val daysSinceInstall = calculateDaysSinceInstall(installDate)
    var daysSinceInstallTo20 = daysSinceInstall
    val quoteIndex: Long
    if(daysSinceInstall<20)
    {
        quoteIndex = daysSinceInstall
    }
    else
    {
        while(daysSinceInstallTo20>=20)
        {
            daysSinceInstallTo20=-20
        }

        quoteIndex = daysSinceInstallTo20
    }

    var userCheckEmotions = false

    LazyColumn(
        modifier = modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                Image(
                    painter = painterResource(R.drawable.logo2),
                    contentDescription = null,
                    modifier = Modifier.width(300.dp)
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                Text(
                    text = stringResource(R.string.psychea),
                    style = TextStyle(
                        fontSize = 50.sp,
                        color = Color.White,
                        shadow = Shadow(
                            color = Color.LightGray, offset = Offset(10.0f, 10.0f), blurRadius = 3f
                        )
                    )
                )

                Text(text = stringResource(R.string._20_dni_do_zdrowej_psychiki),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = 25.sp)
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                if(daysSinceInstall>=10) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = modifier
                            .padding(10.dp)
                            .background(MaterialTheme.colorScheme.secondary)
                    ) {
                        Column(
                            modifier = modifier
                        ) {
                            Text(
                                text = stringResource(R.string.komunikat),
                                color = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier.padding(16.dp)
                            )
                            ClickableFormLink()
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                Column(
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .fillMaxWidth()

                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(dimensionResource(R.dimen.padding_small))
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "$daysSinceInstall",
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 60.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Text(
                                text = stringResource(R.string.dzie),
                                fontStyle = FontStyle.Italic,
                                fontSize = 30.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_format_quote_24),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .size(35.dp)
                        )
                        Text(
                            text = stringResource(quotes_list[quoteIndex.toInt()].quote),
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 36.dp, top = 36.dp),
                            fontStyle = FontStyle.Italic,
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_format_quote_24),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(35.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.tertiaryContainer
                            )
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = stringResource(R.string.ocen_jak_mija_ci_dzisiaj_dzien),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 18.sp
                            )
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                IconButton(
                                    onClick = {},
                                    modifier = Modifier.padding(4.dp)
                                ) {
                                    Icon(
                                        painterResource(id = R.drawable.big_happy),
                                        contentDescription = stringResource(R.string.wspaniale)
                                    )
                                }
                                IconButton(
                                    onClick = {},
                                    modifier = Modifier.padding(4.dp)
                                ) {
                                    Icon(
                                        painterResource(id = R.drawable.happy),
                                        contentDescription = stringResource(R.string.dobrze)
                                    )
                                }
                                IconButton(
                                    onClick = {},
                                    modifier = Modifier.padding(4.dp)
                                ) {
                                    Icon(
                                        painterResource(id = R.drawable.neutral),
                                        contentDescription = stringResource(R.string.nic_specjalnego)
                                    )
                                }
                                IconButton(
                                    onClick = {},
                                    modifier = Modifier.padding(4.dp)
                                ) {
                                    Icon(
                                        painterResource(id = R.drawable.sad),
                                        contentDescription = stringResource(R.string.slabo)
                                    )
                                }
                                IconButton(
                                    onClick = {},
                                    modifier = Modifier.padding(4.dp)
                                ) {
                                    Icon(
                                        painterResource(id = R.drawable.super_sad_emoji),
                                        contentDescription = stringResource(R.string.bardzo_zle)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

            }
        }

        item {
            Row {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.padding_medium)
                    )
                ) {
                    screens.forEach { item ->
                        SelectQuantityButton(
                            labelResourceId = item.title,
                            onClick = { onNextButtonClicked(item) }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SelectQuantityButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
        Text(
            stringResource(labelResourceId),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}


@Composable
fun calculateDaysSinceInstall(installDate: String): Long {
    val currentDate = LocalDate.now()
    val startDate = LocalDate.parse(installDate)
    return ChronoUnit.DAYS.between(startDate, currentDate)
}

@Composable
fun getInstallDate(): String {
    val sharedPreferences: SharedPreferences =
        LocalContext.current.getSharedPreferences("InstallDate", Context.MODE_PRIVATE)
    var installDate = sharedPreferences.getString("InstallDate", "")

    if (installDate.isNullOrBlank()) {
        val currentDate = LocalDate.now().toString()
        sharedPreferences.edit {
            putString("InstallDate", currentDate)
        }
        installDate = currentDate
    }

    return installDate
}

@Composable
fun ClickableFormLink() {
    val formLink = "https://forms.gle/a5au7aDKoGpmG83Y6"
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(formLink))
            context.startActivity(intent)
        },
        modifier = Modifier.padding(16.dp),
    ) {
        Text(
            stringResource(R.string.we_udzia),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}
