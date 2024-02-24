package com.example.basicpsychea.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.basicpsychea.R
import com.example.basicpsychea.data.todoList

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToDoScreen() {
    val installDate = getInstallDate()
    val daysSinceInstall = calculateDaysSinceInstall(installDate)
    var daysSinceInstallTo20 = daysSinceInstall
    val todoId: Long
    if(daysSinceInstall<20)
    {
        todoId = daysSinceInstall
    }
    else
    {
        while(daysSinceInstallTo20>=20)
        {
            daysSinceInstallTo20=-20
        }

        todoId = daysSinceInstallTo20
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
                Card(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ){
                    Row {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp).align(Alignment.CenterVertically).padding(start = 8.dp)
                        )
                        Text(
                            text = stringResource(todoList[todoId.toInt()].todo1),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            item {
                Card(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)){
                    Row {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp).align(Alignment.CenterVertically).padding(start = 8.dp)
                        )
                        Text(
                            text = stringResource(todoList[todoId.toInt()].todo2),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}