package com.example.basicpsychea.ui.screens.tools

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.basicpsychea.data.mood.Mood
import com.example.basicpsychea.data.mood.MoodDatabase
import com.example.basicpsychea.data.mood.MoodRepository
import com.example.basicpsychea.data.mood.OfflineMoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MoodViewModel(application: Application, private val repository: MoodRepository) : AndroidViewModel(application) {

}

