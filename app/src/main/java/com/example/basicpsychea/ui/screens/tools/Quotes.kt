package com.example.basicpsychea.ui.screens.tools

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicpsychea.R
import com.example.basicpsychea.data.QuotesData
import com.example.basicpsychea.data.quotes_list

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QuotesScreen() {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(
                stringResource(id = R.string.twoje_cytaty),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
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
                            stringResource(id = R.string.info_quotes)
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text(stringResource(R.string.ok))
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn {
            items(quotes_list) { quotesItem ->
                QuoteItem(quote = quotesItem)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QuoteItem(quote: QuotesData) {
    val installDate = com.example.basicpsychea.ui.screens.getInstallDate()
    val daysSinceInstall =
        com.example.basicpsychea.ui.screens.calculateDaysSinceInstall(installDate)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            if (daysSinceInstall+1>=quote.id)
            {
                Column {
                    Text(text = stringResource(id = quote.quote))

                    ShareTextButton(text = stringResource(id = quote.quote))
                }
            }
            else
            {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_lock_24),
                        contentDescription = stringResource(R.string.lock),
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                    )
                    Text(text = stringResource(R.string.zablokowane), modifier = Modifier.padding(top = 10.dp), textAlign = TextAlign.Left)

                }
            }
        }
    }
}

@Composable
fun ShareTextButton(text: String) {
    val context = LocalContext.current
    val stext = "\"$text\""
    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            onClick = {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, stext)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(Icons.Filled.Share, contentDescription = stringResource(R.string.share))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun QuotePrev()
{
    QuotesScreen()
}