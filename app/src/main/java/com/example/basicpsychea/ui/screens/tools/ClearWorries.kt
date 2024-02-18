package com.example.basicpsychea.ui.screens.tools

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.tween

import com.example.basicpsychea.R
import kotlinx.coroutines.delay
import java.util.stream.IntStream.range

@Composable
fun ClearWorriesScreen() {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }
    var showDialog by remember { mutableStateOf(false) }
    var shakeAnimation by remember { mutableStateOf(false) }
    var tiltAnimation by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { showDialog = true },
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(Icons.Filled.Info, contentDescription = stringResource(R.string.info))
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(stringResource(R.string.informacja)) },
                text = {
                    Text(
                        stringResource(R.string.worries_info)
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(stringResource(R.string.ok))
                    }
                }
            )
        }

        TextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .graphicsLayer {
                    if (shakeAnimation) {
                        for(i in 0 until 5)
                        {
                            this.rotationZ = (-i).toFloat()
                        }
                    } else if (tiltAnimation) {
                        for(i in 0 until 5)
                        {
                            this.rotationZ = (i).toFloat()
                        }
                    }
                }
                .animateContentSize(),
            placeholder = { Text(stringResource(R.string.wpisz_swoje_zmartwienia)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            tiltAnimation = true
            Handler(Looper.getMainLooper()).postDelayed({
                shakeAnimation = true
                Handler(Looper.getMainLooper()).postDelayed({
                    textFieldValue = TextFieldValue()
                    shakeAnimation = false
                    tiltAnimation = false
                }, 100)
            }, 100)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.usu_zmartwienia))
        }
    }
}


@Composable
@Preview(showBackground = true)
fun WorriesPrev()
{
    ClearWorriesScreen()
}