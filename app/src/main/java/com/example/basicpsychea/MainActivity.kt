package com.example.basicpsychea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.basicpsychea.ui.PsycheaApp
import com.example.basicpsychea.ui.screens.CiekawostkiViewModel
import com.example.basicpsychea.ui.screens.CwiczeniaViewModel
import com.example.basicpsychea.ui.screens.NawykiViewModel
import com.example.basicpsychea.ui.screens.WiedzaViewModel
import com.example.basicpsychea.ui.screens.tools.MoodViewModel
import com.example.basicpsychea.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                PsycheaApp()
            }
        }
    }
}

