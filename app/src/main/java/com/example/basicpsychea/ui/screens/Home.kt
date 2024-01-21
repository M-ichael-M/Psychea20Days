package com.example.basicpsychea.ui.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.example.basicpsychea.PsycheaScreen
import com.example.basicpsychea.R
import com.example.basicpsychea.data.DataSource
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import kotlin.time.ExperimentalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    screens: List<PsycheaScreen>,
    onNextButtonClicked: (PsycheaScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    // Pobierz datę instalacji z SharedPreferences
    val installDate = getInstallDate()
    // Oblicz liczbę dni od instalacji do dzisiaj
    val daysSinceInstall = calculateDaysSinceInstall(installDate)+1

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
                    painter = painterResource(R.drawable.ikona),
                    contentDescription = null,
                    modifier = Modifier.width(300.dp)
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                Text(
                    text = "PSYCHEA",
                    style = MaterialTheme.typography.headlineLarge
                        .copy(
                            color = Color.White,
                            shadow = Shadow(
                                color = Color.Gray,
                                offset = Offset(6f, 6f),
                                blurRadius = 4f
                            ),
                            textAlign = TextAlign.Center
                        )
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 75.dp)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                            .padding(16.dp)
                            .aspectRatio(3f / 2f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(dimensionResource(R.dimen.padding_small)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "$daysSinceInstall",
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 60.sp, // Adjusted font size
                                color = Color.DarkGray,
                            )
                            Text(
                                text = "DZIEŃ",
                                fontStyle = FontStyle.Italic,
                                fontSize = 30.sp, // Adjusted font size
                                color = Color.DarkGray,
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

            }
        }

        item {
            Row() {
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
        Text(stringResource(labelResourceId))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun calculateDaysSinceInstall(installDate: String): Long {
    // Oblicz liczbę dni od daty instalacji do dzisiaj
    val currentDate = LocalDate.now()
    val startDate = LocalDate.parse(installDate)
    return ChronoUnit.DAYS.between(startDate, currentDate)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getInstallDate(): String {
    // Pobierz datę instalacji z SharedPreferences
    val sharedPreferences: SharedPreferences =
        LocalContext.current.getSharedPreferences("InstallDate", Context.MODE_PRIVATE)
    var installDate = sharedPreferences.getString("InstallDate", "")

    if (installDate.isNullOrBlank()) {
        // Jeśli nie ma zapisanej daty instalacji, zapisz aktualną datę
        val currentDate = LocalDate.now().toString()
        sharedPreferences.edit {
            putString("InstallDate", currentDate)
        }
        installDate = currentDate
    }

    return installDate
}
