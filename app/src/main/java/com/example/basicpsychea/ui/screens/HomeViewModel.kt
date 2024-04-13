package com.example.basicpsychea.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicpsychea.data.mood.Mood
import com.example.basicpsychea.ui.MoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = MoodRepository(app.applicationContext)

    fun clearDb() {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.dropDatabase()
        }
    }


    fun insertMood(emotion: Int, daysSinceInstall: Long)
    {
        val time = System.currentTimeMillis()

        val mood = Mood(emotion=emotion, dateOfEmotion = time, daysSinceInstallation = daysSinceInstall.toInt())
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.insertOne(mood)
        }
    }

    fun getLast(): Flow<Long> {
        return repo.getLast()
    }

    fun insertOutstandingMood(mood: Mood)
    {
        CoroutineScope(viewModelScope.coroutineContext).launch{ repo.insertOne(mood) }
    }

    fun getEmotionByDate(day: Int): Flow<Int>
    {
        return repo.getEmotionByDate(day)
    }
}