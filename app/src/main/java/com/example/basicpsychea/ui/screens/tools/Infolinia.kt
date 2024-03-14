package com.example.basicpsychea.ui.screens.tools

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.basicpsychea.R
import com.example.basicpsychea.data.infoliniaData
import com.example.basicpsychea.data.infolinia_list

@Composable
fun InfoliniaScreen() {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        item {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(R.string.telefony_wsprcia_infolinia_jest_bezp_atne_dzwonienie_na_te_numery_jest_pierwsz_rzecz_jak_mo_emy_zrobi_gdy_nasz_stan_si_pogarsza_a_nie_mo_emy_uda_si_do_psychologa),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }

        items(infolinia_list) { infoliniaItem ->
            ClickablePhoneNumber(exercises = infoliniaItem)
        }
    }
}

@Composable
fun ClickablePhoneNumber(exercises: infoliniaData) {
    val phoneNumber = stringResource(exercises.phoneNumber)
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .height(40.dp),
            painter = painterResource(R.drawable.baseline_phone_24),
            contentDescription = null
        )
        Text(
            stringResource(exercises.title),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}