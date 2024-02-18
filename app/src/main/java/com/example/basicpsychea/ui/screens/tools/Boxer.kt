package com.example.basicpsychea.ui.screens.tools

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicpsychea.R
@Composable
fun BoxerScreen(modifier: Modifier) {
    var isClicked by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val targetSize = if (isClicked) 1000.dp else 1000.dp
    val size by animateDpAsState(targetValue = targetSize, animationSpec = tween(1000), label = "")

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = modifier
                .size(size)
                .clickable { isClicked = !isClicked },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.blank),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }

        IconButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Info, contentDescription = stringResource(R.string.info))
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(stringResource(R.string.informacja)) },
                text = {
                    Text(stringResource(R.string.waves_info))
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(stringResource(id = R.string.ok))
                    }
                }
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun BoxerPrev() {
    BoxerScreen(modifier = Modifier)
}
