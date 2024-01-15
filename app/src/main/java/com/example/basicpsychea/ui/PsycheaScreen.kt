/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.basicpsychea

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.basicpsychea.R
import com.example.basicpsychea.data.DataSource
import com.example.basicpsychea.ui.screens.Ciekawostki
import com.example.basicpsychea.ui.screens.Cwiczenia
import com.example.basicpsychea.ui.screens.HomeScreen
import com.example.basicpsychea.ui.screens.Wiedza
import com.example.basicpsychea.ui.screens.my
import com.example.basicpsychea.ui.screens.nawyki
import com.example.basicpsychea.ui.screens.projekt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PsycheaAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("Psychea") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun PsycheaApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen

    PsycheaAppBar(true, navigateUp = { navController.popBackStack() })

    NavHost(navController = navController, startDestination = "home") {
        composable("Home") {
            HomeScreen(
                screens = DataSource.screens,
                onNextButtonClicked = { screenId ->
                    // Navigate to the selected screen
                    navController.navigate("screens/$screenId")
                }
            )
        }
        composable("Wiedza") {
            Wiedza(
                onNextButtonClicked = { screenId ->
                    // Navigate to the selected screen
                    navController.navigate("screens/$screenId")
                }
            )
        }
        composable("Ciekawostki") {
            Ciekawostki(
                onNextButtonClicked = { screenId ->
                    // Navigate to the selected screen
                    navController.navigate("screens/$screenId")
                }
            )
        }
        composable("Cwiczenia") {
            Cwiczenia(
                onNextButtonClicked = { screenId ->
                    // Navigate to the selected screen
                    navController.navigate("screens/$screenId")
                }
            )
        }
        composable("Nawyki") {
            nawyki(
                onNextButtonClicked = { screenId ->
                    // Navigate to the selected screen
                    navController.navigate("screens/$screenId")
                }
            )
        }
        composable("Projekt") {
            projekt(
                onNextButtonClicked = { screenId ->
                    // Navigate to the selected screen
                    navController.navigate("screens/$screenId")
                }
            )
        }
        composable("My") {
            my(
                onNextButtonClicked = { screenId ->
                    // Navigate to the selected screen
                    navController.navigate("screens/$screenId")
                }
            )
        }

    }
}

@Composable
@Preview
fun Preview()
{
    PsycheaApp()
}