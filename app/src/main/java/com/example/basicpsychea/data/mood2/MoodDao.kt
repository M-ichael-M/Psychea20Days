package com.example.basicpsychea.data.mood2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {
    @Insert
    suspend fun insertAll(moods: List<Mood>)

    @Delete
    suspend fun delete(moods: List<Mood>)

    @Update
    suspend fun update(mood: Mood)
    @Query("SELECT * FROM user_mood")
    fun getAll(): Flow<List<Mood>>

    @Query("DELETE FROM user_mood")
    suspend fun dropDatabase()

    @Query("SELECT dateOfEmotion FROM user_mood " +
            "ORDER BY id DESC " +
            "LIMIT 1;")
    fun getLast(): Flow<Double>
}