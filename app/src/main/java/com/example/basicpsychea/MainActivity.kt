package com.example.basicpsychea

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.basicpsychea.ui.screens.CiekawostkiViewModel
import com.example.basicpsychea.ui.screens.CwiczeniaViewModel
import com.example.basicpsychea.ui.screens.NawykiViewModel
import com.example.basicpsychea.ui.screens.WiedzaViewModel
import com.example.basicpsychea.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    private val ciekawostkiViewModel by viewModels<CiekawostkiViewModel>()
    private val cwiczeniaViewModel by viewModels<CwiczeniaViewModel>()
    private val nawykiViewModel by viewModels<NawykiViewModel>()
    private val wiedzaViewModel by viewModels<WiedzaViewModel>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                PsycheaApp(viewModelCiekawostki = ciekawostkiViewModel, viewModelCwiczenia = cwiczeniaViewModel, viewModelNawyki = nawykiViewModel, viewModelWiedza = wiedzaViewModel)
            }
        }

    }
}

