package com.example.basicpsychea.ui

import android.content.Context
import com.example.basicpsychea.data.goals.Goal
import com.example.basicpsychea.data.goals.GoalDao
import com.example.basicpsychea.data.mood.MoodDb
import kotlinx.coroutines.flow.Flow

class GoalRepository(context: Context): GoalDao {
    private val dao = MoodDb.getInstance(context).goalDao()
    override suspend fun dropDatabase() {
        dao.dropDatabase()
    }

    override fun getAll(): Flow<List<Goal>> {
        return dao.getAll()
    }

    override fun getToDoByDay(days: Long): Flow<Int> {
        return dao.getToDoByDay(days)
    }

    override suspend fun insertRecord(goal: Goal) {
        dao.insertRecord(goal)
    }

    override suspend fun updateReocrd(todo: Int) {
        dao.updateReocrd(todo)
    }

    override fun getLastRecord(): Flow<Goal> {
        return dao.getLastRecord()
    }
}