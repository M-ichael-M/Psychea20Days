package com.example.basicpsychea.ui.screens.tools

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basicpsychea.R
import com.example.basicpsychea.data.mood.Mood
import com.example.basicpsychea.ui.screens.calculateDaysSinceInstall
import com.example.basicpsychea.ui.screens.getInstallDate
import kotlinx.coroutines.flow.firstOrNull
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun MoodsScreen(viewModel: MoodViewModel) {
    val daysSinceInstall = calculateDaysSinceInstall(installDate = getInstallDate())
    val today = System.currentTimeMillis()
    var lastRecord by remember { mutableLongStateOf(0L) }
    val dateOfInstalation: Calendar = Calendar.getInstance().apply {
        timeInMillis = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(getInstallDate())!!.time
    }

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

    Column {
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            item {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    Text(text = moods.toString())
                    if (!userCheckEmotions) {
                        NewEmotion(viewModel = viewModel, daysSinceInstall)
                    } else {
                        UpdateEmotion(viewModel = viewModel, daysSinceInstall)
                    }
                }
            }
            //item { ColumnChartV2(viewModel, today, daysSinceInstall) }

            items(moodsRev) {
                mood ->
                EmocjeItem(emocja = mood, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun ColumnChartV2(viewModel: MoodViewModel, today: Long, daysSinceInstall: Long) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())


    val emocjeList = remember { mutableListOf<Int>() }

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = today
    var dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    dayOfWeek = (dayOfWeek + 5) % 7

    val calendarOdEnd = Calendar.getInstance()
    val installDate = dateFormat.parse(getInstallDate())
    calendarOdEnd.time = installDate!!
    var dayOfWeekEnd = calendarOdEnd.get(Calendar.DAY_OF_WEEK)
    dayOfWeekEnd = (dayOfWeekEnd + 5) % 7

    val odPoczatku = dayOfWeek
    val odKonca = 6 - dayOfWeekEnd

    repeat(odPoczatku) {
        emocjeList.add(0)
    }

    LaunchedEffect(Unit) {
        repeat(daysSinceInstall.toInt()) { i ->
            val emocjaFlow = viewModel.getEmotionByDate(i)
            emocjaFlow.firstOrNull()?.let { emocja ->
                emocjeList.add(emocja)
            } ?: emocjeList.add(0)
        }
    }

    repeat(odKonca) {
        emocjeList.add(0)
    }


}

@Composable
fun EmotionsChart(emotions: List<Int>) {
    val maxValue = 5 // maksymalna wartość emocji
    val maxColumnHeight = 200.dp // maksymalna wysokość kolumny w pikselach
    val columnWidth = 30.dp // szerokość kolumny
    val columnSpacing = 10.dp // odstęp między kolumnami

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Canvas(modifier = Modifier.fillMaxWidth()) {
            emotions.forEachIndexed { index, emotion ->
                val columnHeight = (emotion.toFloat() / maxValue.toFloat()) * maxColumnHeight.value
                val startX = index * (columnWidth.value + columnSpacing.value)
                val startY = size.height - columnHeight

                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(startX, startY),
                    size = Size(columnWidth.value, columnHeight)
                )
            }
        }
    }
}

@Composable
fun EmocjeItem(emocja: Mood, viewModel: MoodViewModel) {
    var expanded by remember {
        mutableStateOf(viewModel.expandedStateMap[emocja.id] ?: false)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.padding_small))) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
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
                Box(Modifier.fillMaxWidth()) {
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
                            ))
                            Text(text = stringResource(R.string.dzien))
                        }
                    }

                    Button(
                        onClick = {
                            expanded = !expanded
                            viewModel.expandedStateMap[emocja.id] = expanded
                        },
                        Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight(),
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
                Text("roz")
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