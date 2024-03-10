package com.example.basicpsychea.ui

import android.app.Application
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicpsychea.data.mood2.Mood
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date

class MoodViewModel(app: Application) : AndroidViewModel(app){
    private val repo = MoodRepository(app.applicationContext)

    fun getMoods():Flow<List<Mood>>
    {
        return repo.getAll()
    }

    fun getLast(): Flow<Double>
    {
        return repo.getLast()
    }

    fun dropDatabase()
    {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repo.dropDatabase()

        }
    }

    fun insertMood(emotion: Int)
    {
        val mood = Mood(emotion = emotion, dateOfEmotion = getCurrentDateInDouble())
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.insertAll(listOf(mood))
        }
    }

    private fun getCurrentDateInDouble(): Double {
        val date = Date()
        return date.time.toDouble()
    }
}