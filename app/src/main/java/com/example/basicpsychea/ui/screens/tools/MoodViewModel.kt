package com.example.basicpsychea.ui.screens.tools

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicpsychea.data.mood.Mood
import com.example.basicpsychea.ui.GoalRepository
import com.example.basicpsychea.ui.MoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MoodViewModel(app: Application) : AndroidViewModel(app) {
    val expandedStateMap = mutableMapOf<Int, Boolean>()

    private val repo = MoodRepository(app.applicationContext)
    private val repoGoal = GoalRepository(app.applicationContext)

    fun getMoods(): Flow<List<Mood>> {
        return repo.getAll()
    }

    fun updateMood(emotion: Int, id: Int, daysSinceInstall: Long)
    {
        val time = System.currentTimeMillis()

        val mood = Mood(id = id, emotion=emotion, dateOfEmotion = time, daysSinceInstall.toInt())
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.update(mood)
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

    fun getLastId(): Flow<Int>
    {
        return repo.getLastId()
    }

    fun getTodoByDay(day: Long): Flow<Int>
    {
        return repoGoal.getToDoByDay(day)
    }
}