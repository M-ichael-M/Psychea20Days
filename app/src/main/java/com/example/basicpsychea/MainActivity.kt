package com.example.basicpsychea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.basicpsychea.ui.MoodViewModel
import com.example.basicpsychea.ui.PsycheaApp
import com.example.basicpsychea.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    private val moodViewM by viewModels<MoodViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                PsycheaApp(moodViewModel = moodViewM)
            }
        }
    }
}

