package com.example.basicpsychea.ui.screens.tools

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basicpsychea.R
import com.example.basicpsychea.data.mood.Mood
import com.example.basicpsychea.ui.screens.calculateDaysSinceInstall
import com.example.basicpsychea.ui.screens.getInstallDate
import kotlinx.coroutines.flow.firstOrNull
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Calendar

@Composable
fun MoodsScreen(viewModel: MoodViewModel) {
    val daysSinceInstall = calculateDaysSinceInstall(installDate = getInstallDate())
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

    val moods: List<Mood> by viewModel.getMoods().collectAsState(initial = emptyList())
    val moodsRev = moods.reversed()
    val averageEmotion: Double = if (moods.isNotEmpty()) {
        val sum = moods.map { it.emotion }.sum()
        val average = sum.toDouble() / moods.size
        BigDecimal(average).setScale(1, RoundingMode.HALF_UP).toDouble()
    } else {
        0.0 // Lub dowolna wartość domyślna, jeśli lista jest pusta
    }

    Column {
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(8.dp) // Zaokrąglenie rogów
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Column {
                            Text(
                                text = stringResource(R.string.twoja_rednia_emocji_od_zainstalowania_aplikacji),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                color = Color.Black, // Kolor tekstu
                                fontSize = 18.sp, // Rozmiar tekstu
                            )
                            Text(
                                text = averageEmotion.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp),
                                color = Color.Black, // Kolor tekstu
                                fontSize = 24.sp, // Rozmiar tekstu
                                fontWeight = FontWeight.Bold // Wytłuszczenie tekstu
                            )
                        }
                    }
                }

            }
            item {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    if (!userCheckEmotions) {
                        NewEmotion(viewModel = viewModel, daysSinceInstall)
                    } else {
                        UpdateEmotion(viewModel = viewModel, daysSinceInstall)
                    }
                }
            }
            
            items(moodsRev) {
                mood ->
                EmocjeItem(emocja = mood, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun EmocjeItem(emocja: Mood, viewModel: MoodViewModel) {
    var todoDb by remember { mutableIntStateOf(5) }
    var recordExist by remember { mutableStateOf(false) }
    var isChecked1 by remember {
        mutableStateOf(false)
    }
    var isChecked2  by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.getTodoByDay(emocja.daysSinceInstallation.toLong()).firstOrNull()?.let {
            todoDb = it
        }

        when (todoDb) {
            1 -> {
                recordExist = true
                isChecked1 = true
                isChecked2 = false
            }
            2 -> {
                recordExist = true
                isChecked1 = false
                isChecked2 = true
            }
            3 -> {
                recordExist = true
                isChecked1 = true
                isChecked2 = true
            }
            0 -> {
                recordExist = true
                isChecked1 = false
                isChecked2 = false
            }
            5 -> {
                recordExist = false
            }

        }
    }
    
    var expanded by remember {
        mutableStateOf(viewModel.expandedStateMap[emocja.id] ?: false)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.padding_small)),
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
        ) {
            val emocjaA = emocja.emotion
            val drawable: Painter
            when (emocjaA) {
                1 -> {
                    drawable = painterResource(id = R.drawable.super_sad_emoji)
                }
                2 -> {
                    drawable = painterResource(id = R.drawable.sad)
                }
                3 -> {
                    drawable = painterResource(id = R.drawable.neutral)
                }
                4 -> {
                    drawable = painterResource(id = R.drawable.happy)
                }
                else -> {
                    drawable = painterResource(id = R.drawable.big_happy)
                }
            }

            Row(horizontalArrangement = Arrangement.Absolute.Center) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)) {
                    Row {
                        Image(
                            painter = drawable,
                            contentDescription = null,
                            Modifier
                                .padding(16.dp)
                                .size(50.dp)
                                .align(Alignment.CenterVertically)
                        )

                        Column(Modifier.align(Alignment.CenterVertically)) {
                            Text(text = emocja.daysSinceInstallation.toString(), Modifier.align(
                                Alignment.CenterHorizontally
                            ), color = MaterialTheme.colorScheme.onPrimary)
                            Text(text = stringResource(R.string.dzien), color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }

                    Button(
                        onClick = {
                            expanded = !expanded
                            viewModel.expandedStateMap[emocja.id] = expanded
                        },
                        Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            ,
                        shape = CircleShape
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                            contentDescription = stringResource(R.string.arrow_down)
                        )
                    }
                }
            }
            if (expanded) {
                Box(Modifier.padding(16.dp)){
                    if(isChecked1 && isChecked2)
                    {
                        Text(text = stringResource(R.string.wszystkie_todo))
                    }
                    else if((isChecked1 && !isChecked2) || (!isChecked1 && isChecked2))
                    {
                        Text(text = stringResource(R.string.jedno_todo))
                    }
                    else
                    {
                        Text(text = stringResource(R.string.zero_todo))
                    }
                }
            }
        }
    }
}

@Composable
fun NewEmotion(viewModel: MoodViewModel, daysSinceInstall: Long)
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
                                if(isVisible) viewModel.insertMood(1, daysSinceInstall)
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
            UpdateEmotion(viewModel = viewModel, daysSinceInstall = daysSinceInstall)
        }
    }
}

@Composable
fun UpdateEmotion(viewModel: MoodViewModel, daysSinceInstall: Long)
{
    var lastId by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        viewModel.getLastId().firstOrNull()?.let {
            lastId = it
        }
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
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
                        text = stringResource(R.string.zmieni_mi_si_dzi_nastr_j),
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
                                viewModel.updateMood(5, lastId, daysSinceInstall)
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
                                viewModel.updateMood(4, lastId, daysSinceInstall)
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
                                viewModel.updateMood(3, lastId, daysSinceInstall)
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
                                viewModel.updateMood(2, lastId, daysSinceInstall)
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
                                viewModel.updateMood(1, lastId, daysSinceInstall)
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
}