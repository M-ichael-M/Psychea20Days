package com.example.basicpsychea.ui.screens.tools

import android.content.Context
import android.content.SharedPreferences
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import com.example.basicpsychea.R
import com.example.basicpsychea.data.QuotesData
import com.example.basicpsychea.data.quotes_list
import java.time.LocalDate
import java.time.temporal.ChronoUnit
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QuotesScreen() {

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
        }
        Spacer(modifier = Modifier.height(16.dp)) // Dodajemy odstęp między nagłówkiem a pozostałą zawartością
        LazyColumn {
            items(quotes_list) { quotesItem ->
                QuoteItem(quote = quotesItem)
                Spacer(modifier = Modifier.height(8.dp)) // Dodajemy odstęp między cytatami
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
                Text(text = stringResource(id = quote.quote))
            }
            else
            {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_lock_24),
                        contentDescription = "lock",
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                    )
                    Text(text = "Zablokowane", modifier = Modifier.padding(top = 10.dp))

                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun calculateDaysSinceInstall(installDate: String): Long {
    val currentDate = LocalDate.now()
    val startDate = LocalDate.parse(installDate)
    return ChronoUnit.DAYS.between(startDate, currentDate)
}

@RequiresApi(Build.VERSION_CODES.O)
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun QuotePrev()
{
    QuotesScreen()
}