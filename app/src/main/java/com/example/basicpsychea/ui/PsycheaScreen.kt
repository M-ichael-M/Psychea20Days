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
package com.example.basicpsychea.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.basicpsychea.R
import com.example.basicpsychea.ui.screens.ArticleScreen
import com.example.basicpsychea.ui.screens.Ciekawostki
import com.example.basicpsychea.ui.screens.CiekawostkiViewModel
import com.example.basicpsychea.ui.screens.Cwiczenia
import com.example.basicpsychea.ui.screens.CwiczeniaViewModel
import com.example.basicpsychea.ui.screens.HomeScreen
import com.example.basicpsychea.ui.screens.NawykiViewModel
import com.example.basicpsychea.ui.screens.ToDoScreen
import com.example.basicpsychea.ui.screens.ToolsScreen
import com.example.basicpsychea.ui.screens.Wiedza
import com.example.basicpsychea.ui.screens.WiedzaViewModel
import com.example.basicpsychea.ui.screens.my
import com.example.basicpsychea.ui.screens.nawyki
import com.example.basicpsychea.ui.screens.projekt
import com.example.basicpsychea.ui.screens.tools.BoxerScreen
import com.example.basicpsychea.ui.screens.tools.ClearWorriesScreen
import com.example.basicpsychea.ui.screens.tools.InfoliniaScreen
import com.example.basicpsychea.ui.screens.tools.QuotesScreen


enum class PsycheaScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Wiedza(title = R.string.strefa_wiedzy),
    Ciekawostki(title = R.string.strefa_ciekawostek),
    Cwiczenia(title = R.string.strefa_wicze),
    Nawyki(title = R.string.zdrowe_nawyki),
    Narzedzia(title = R.string.narz_dzia),
    Projekt(title = R.string.o_projekcie),
    My(title = R.string.o_nas),
    ToDo(title = R.string.todo),
    Articles(title = R.string.articles),

    Infolinia(title = R.string.infolinia),
    Boxer(title = R.string.boxer),
    ClearWorries(title = R.string.clearworries),
    Quotes(title = R.string.twoje_cytaty)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PsycheaAppBar(
    currentScreen: PsycheaScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
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
fun PsycheaApp(navController: NavController = rememberNavController(), viewModelCiekawostki: CiekawostkiViewModel, viewModelCwiczenia: CwiczeniaViewModel, viewModelNawyki: NawykiViewModel, viewModelWiedza: WiedzaViewModel) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PsycheaScreen.valueOf(
        backStackEntry?.destination?.route ?: PsycheaScreen.Start.name
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PsycheaAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        NavHost(navController = navController as NavHostController, startDestination = PsycheaScreen.Start.name) {
            composable(PsycheaScreen.Start.name) {
                HomeScreen(
                    screens = listOf(
                        PsycheaScreen.ToDo,
                        PsycheaScreen.Articles,
                        PsycheaScreen.Narzedzia,
                        PsycheaScreen.Projekt,
                        PsycheaScreen.My
                    ),
                    onNextButtonClicked = { screen ->
                        when (screen) {
                            PsycheaScreen.ToDo -> navController.navigate(PsycheaScreen.ToDo.name)
                            PsycheaScreen.Articles -> navController.navigate(PsycheaScreen.Articles.name)
                            PsycheaScreen.Narzedzia -> navController.navigate(PsycheaScreen.Narzedzia.name)
                            PsycheaScreen.Projekt -> navController.navigate(PsycheaScreen.Projekt.name)
                            PsycheaScreen.My -> navController.navigate(PsycheaScreen.My.name)

                            else -> {}
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            composable(PsycheaScreen.Articles.name)
            {
                ArticleScreen(screens = listOf(PsycheaScreen.Wiedza,
                    PsycheaScreen.Ciekawostki,
                    PsycheaScreen.Cwiczenia,
                    PsycheaScreen.Nawyki), onNextButtonClicked ={screen ->
                    when (screen) {
                        PsycheaScreen.Wiedza -> navController.navigate(PsycheaScreen.Wiedza.name)
                        PsycheaScreen.Ciekawostki ->navController.navigate(PsycheaScreen.Ciekawostki.name)
                        PsycheaScreen.Cwiczenia -> navController.navigate(PsycheaScreen.Cwiczenia.name)
                        PsycheaScreen.Nawyki -> navController.navigate(PsycheaScreen.Nawyki.name)

                        else -> {}
                    }
                })
            }

            composable(PsycheaScreen.Wiedza.name) {
                Wiedza(viewModel = viewModelWiedza)
            }

            composable(PsycheaScreen.Ciekawostki.name) {
                Ciekawostki(viewModel = viewModelCiekawostki)
            }

            composable(PsycheaScreen.Cwiczenia.name) {
                Cwiczenia(viewModel = viewModelCwiczenia)
            }

            composable(PsycheaScreen.Nawyki.name) {
                nawyki(viewModel = viewModelNawyki)
            }

            composable(PsycheaScreen.Narzedzia.name){
                ToolsScreen(
                    screens = listOf(
                        PsycheaScreen.Infolinia,
                        PsycheaScreen.Boxer,
                        PsycheaScreen.ClearWorries,
                        PsycheaScreen.Quotes
                ), onNextButtonClicked = {screen ->
                when (screen) {
                    PsycheaScreen.Infolinia -> navController.navigate(PsycheaScreen.Infolinia.name)
                    PsycheaScreen.Boxer ->navController.navigate(PsycheaScreen.Boxer.name)
                    PsycheaScreen.ClearWorries -> navController.navigate(PsycheaScreen.ClearWorries.name)
                    PsycheaScreen.Quotes -> navController.navigate(PsycheaScreen.Quotes.name)

                    else -> {}
                }
                })
            }

            composable(PsycheaScreen.Projekt.name) {
                projekt()
            }

            composable(PsycheaScreen.My.name) {
                my()
            }

            composable(PsycheaScreen.Infolinia.name)
            {
                InfoliniaScreen()
            }

            composable(PsycheaScreen.Boxer.name)
            {
                BoxerScreen(modifier = Modifier)
            }

            composable(PsycheaScreen.ClearWorries.name)
            {
                ClearWorriesScreen()
            }

            composable(PsycheaScreen.Quotes.name)
            {
                QuotesScreen()
            }

            composable(PsycheaScreen.ToDo.name)
            {
                ToDoScreen()
            }
        }
    }
}

@Composable
@Preview
fun Preview() {
    //PsycheaApp()
}
