package com.example.basicpsychea.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicpsychea.data.goals.Goal
import com.example.basicpsychea.ui.GoalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch



class ToDoViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = GoalRepository(app.applicationContext)

    init {
        dropDb()
    }
    fun dropDb()
    {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.dropDatabase()
        }
    }
    fun getAll(): Flow<List<Goal>>
    {
        return repo.getAll()
    }

    fun getToDoByDay(days: Long): Flow<Int>
    {
        return repo.getToDoByDay(days)
    }

    fun updateRecord(todo: Int, days: Int)
    {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.updateReocrd(todo)
        }
    }

    fun insertRecord(todo: Int, days: Int)
    {
        val list = listOf(0,0,0,0)
        val userText = listOf("")
        val goal = Goal(toDo = todo, tools = list, userText = userText, daysSinceInstallation = days)
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.insertRecord(goal)
        }
    }

    fun getLastRecord()
    {
        repo.getLastRecord()
    }
}