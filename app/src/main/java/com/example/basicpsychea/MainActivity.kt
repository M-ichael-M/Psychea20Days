package com.example.basicpsychea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.basicpsychea.ui.PsycheaApp
import com.example.basicpsychea.ui.screens.HomeViewModel
import com.example.basicpsychea.ui.screens.ToDoViewModel
import com.example.basicpsychea.ui.screens.tools.MoodViewModel
import com.example.basicpsychea.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    private val moodVm by viewModels<MoodViewModel>()
    private val homeVm by viewModels<HomeViewModel>()
    private val todoVm by viewModels<ToDoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                PsycheaApp(moodViewModel = moodVm, homeViewModel = homeVm, toDoViewModel = todoVm)
            }
        }
    }
}