package com.example.basicpsychea.data.goals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    @Query("DELETE FROM user_goals")
    suspend fun dropDatabase()

    @Query("SELECT * FROM user_goals")
    fun getAll(): Flow<List<Goal>>

    @Query("SELECT todo FROM user_goals WHERE daysSinceInstallation = :days")
    fun getToDoByDay(days: Long): Flow<Int>

    @Insert
    suspend fun insertRecord(goal: Goal)

    @Query("UPDATE user_goals SET toDo = :todo WHERE id = (SELECT MAX(id) FROM user_goals)")
    suspend fun updateReocrd(todo: Int)

    @Query("SELECT * FROM user_goals LIMIT 1")
    fun getLastRecord(): Flow<Goal>

}