package com.example.basicpsychea.ui.screens.tools

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.basicpsychea.R

@Composable
fun PlaylistyScreen()
{
    LazyColumn {
        item {
            Box(Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                ClickableSpotifyLink(
                    link = "https://open.spotify.com/album/1mx07Kyf6DOIPYoad9Cail?si=o_UBXtqlTsywL8DpQ5AFTA",
                    stringResource(
                        R.string.szumy
                    )
                )
            }
        }

        item{
            Box(Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ClickableSpotifyLink(
                    link = "https://open.spotify.com/album/0gNHBmdvhktPBwbFcv9Hd7?si=suBBIL7pTAK0F9d9fzWWhQ",
                    stringResource(
                        R.string.relax
                    )
                )
            }
        }
        
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ClickableSpotifyLink(
                    link = "https://open.spotify.com/playlist/2EpVGOv7T9obdQ8nQJJ6AM?si=a8487a0ffade4b6f",
                    stringResource(R.string.klasyki)
                )
            }
        }
    }
}

@Composable
fun ClickableSpotifyLink(link: String, etykieta: String) {
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .height(40.dp),
            painter = painterResource(R.drawable.spotfy),
            contentDescription = null
        )
        Text(
            etykieta,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}

