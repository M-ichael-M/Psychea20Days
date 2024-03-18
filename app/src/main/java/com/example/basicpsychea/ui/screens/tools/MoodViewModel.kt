package com.example.basicpsychea.ui.screens.tools

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicpsychea.data.mood.Mood
import com.example.basicpsychea.ui.MoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MoodViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = MoodRepository(app.applicationContext)

    fun getMoods(): Flow<List<Mood>> {
        return repo.getAll()
    }

    init
    {
        clearDb()
    }

    fun clearDb() {
            CoroutineScope(viewModelScope.coroutineContext).launch {
                repo.dropDatabase()
        }
    }


}