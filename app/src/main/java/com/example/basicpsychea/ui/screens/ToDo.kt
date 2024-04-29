package com.example.basicpsychea.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.basicpsychea.R
import com.example.basicpsychea.data.goals.Goal
import com.example.basicpsychea.data.todoList
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun ToDoScreen(viewModel: ToDoViewModel) {
    val installDate = getInstallDate()
    val daysSinceInstall = calculateDaysSinceInstall(installDate)
    val todoId = daysSinceInstall % 20

    var todoDb by remember { mutableIntStateOf(5) }
    var recordExist by remember { mutableStateOf(false) }
    var isChecked1 by remember {
        mutableStateOf(false)
    }
    var isChecked2  by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.getToDoByDay(daysSinceInstall).firstOrNull()?.let {
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

        if (!recordExist) {
            viewModel.insertRecord(0, daysSinceInstall.toInt())
            recordExist = true
        }
    }

    var all by remember { mutableStateOf(listOf(Goal(0,0, listOf(0), listOf(""), 0))) }

    LaunchedEffect(Unit) {
        viewModel.getAll().firstOrNull()?.let {
            all = it
        }
    }

    Column {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(
                stringResource(R.string.zadania_na_dzis),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
        }

        LazyColumn()
        {
            item {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = stringResource(todoList[todoId.toInt()].todo1),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                        Checkbox(
                            checked = isChecked1,
                            onCheckedChange = { isChecked1 = it
                                val value: Int

                                if(isChecked1)
                                {
                                    value = if(isChecked2) {
                                        3
                                    } else {
                                        1
                                    }
                                }
                                else
                                {
                                    value = if(isChecked2) {
                                        2
                                    } else{
                                        0
                                    }
                                }

                                viewModel.updateRecord(value)
},
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }


            item {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = stringResource(todoList[todoId.toInt()].todo2),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                        Checkbox(
                            checked = isChecked2,
                            onCheckedChange = {
                                isChecked2 = it
                                val value: Int
                                if(isChecked1)
                                {
                                    value = if(isChecked2) {
                                        3
                                    } else {
                                        1
                                    }
                                }
                                else
                                {
                                    value = if(isChecked2) {
                                        2
                                    } else{
                                        0
                                    }
                                }

                                viewModel.updateRecord(value)
                                              },
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

        }
    }
}
