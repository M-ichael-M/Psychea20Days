package com.example.basicpsychea.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.flow.firstOrNull
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Calendar

@SuppressLint("PrivateResource")
@Composable
fun HomeScreen(
    screens: List<PsycheaScreen>,
    onNextButtonClicked: (PsycheaScreen) -> Unit,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val installDate = getInstallDate()
    val daysSinceInstall = calculateDaysSinceInstall(installDate)
    val quoteIndex = daysSinceInstall%20

    val today = System.currentTimeMillis()
    var lastRecord by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        viewModel.getLast().firstOrNull()?.let {
            lastRecord = it
        }
    }

    val cal1 = Calendar.getInstance().apply {
        timeInMillis = today
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val cal2 = Calendar.getInstance().apply {
        timeInMillis = lastRecord
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val userCheckEmotions = cal1.timeInMillis == cal2.timeInMillis

    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
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

                Text(
                    text = stringResource(R.string._20_dni_do_zdrowej_psychiki),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = 25.sp)
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                if(daysSinceInstall >= 10) {
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

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

            }
            if(!userCheckEmotions)
            {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    var isVisible by remember { mutableStateOf(true) }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn(animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.tertiaryContainer)
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
                                        onClick = {
                                            if(isVisible) viewModel.insertMood(5, daysSinceInstall)
                                            isVisible = false
                                        },
                                        modifier = Modifier.padding(4.dp)
                                    ) {
                                        Icon(
                                            painterResource(id = R.drawable.big_happy),
                                            contentDescription = stringResource(R.string.wspaniale)
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            if(isVisible) viewModel.insertMood(4, daysSinceInstall)
                                            isVisible = false
                                        },
                                        modifier = Modifier.padding(4.dp)
                                    ) {
                                        Icon(
                                            painterResource(id = R.drawable.happy),
                                            contentDescription = stringResource(R.string.dobrze)
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            if(isVisible) viewModel.insertMood(3, daysSinceInstall)
                                            isVisible = false
                                        },
                                        modifier = Modifier.padding(4.dp)
                                    ) {
                                        Icon(
                                            painterResource(id = R.drawable.neutral),
                                            contentDescription = stringResource(R.string.nic_specjalnego)
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            if(isVisible) viewModel.insertMood(2, daysSinceInstall)
                                            isVisible = false
                                        },
                                        modifier = Modifier.padding(4.dp)
                                    ) {
                                        Icon(
                                            painterResource(id = R.drawable.sad),
                                            contentDescription = stringResource(R.string.slabo)
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            if(isVisible) viewModel.insertMood(1,daysSinceInstall)
                                            isVisible = false
                                        },
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

                    AnimatedVisibility(
                        visible = !isVisible,
                        enter = fadeIn(animationSpec = tween(durationMillis = 500, delayMillis = 750, easing = LinearOutSlowInEasing)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing))
                    ) {
                        Porday(viewModel = viewModel, daysSinceInstall = daysSinceInstall)
                    }
                }
            }
            else
            {
                Porday(viewModel = viewModel, daysSinceInstall = daysSinceInstall)

            }


            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

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


        item {
            Image(painter = painterResource(id = R.drawable.stopka), contentDescription = stringResource(
                R.string.calm_down
            ), modifier = Modifier.fillMaxWidth())
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
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            stringResource(R.string.we_udzia),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun Porday(viewModel: HomeViewModel, daysSinceInstall: Long)
{
    var emotionToday by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        viewModel.getEmotionByDate(daysSinceInstall.toInt()).firstOrNull()?.let {
            emotionToday = it
        }
    }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                when (emotionToday) {
                    5 -> {
                        Text(
                            text = stringResource(R.string.wspaniale_ciesz_si_tym_dniem),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    4 -> {
                        Text(text = stringResource(R.string.spraw_by_ten_dzie_by_jeszcze_lepszy_wykonaj_zadania_na_dzi_z_todo),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(text = stringResource(R.string.lokalizacja_todo),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    3 -> {
                        Text(text = stringResource(R.string.playlisty_emocje),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(text = stringResource(R.string.lokalizacja_playlisty),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    2 -> {
                        Text(text = stringResource(R.string.medytacje_emocje),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                        //Text(text = stringResource(R.string.lokalizacja_medytacje),
                       //     style = MaterialTheme.typography.bodyMedium
                        //)
                    }

                    1 -> {

                        Text(
                            text = stringResource(R.string.emocje_liniawsparcia),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = stringResource(R.string.telefonzaufania_lokalizacja),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}