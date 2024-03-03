package com.example.basicpsychea.data.mood

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mood: Mood)

    @Update
    suspend fun update(mood: Mood)

    @Delete
    suspend fun delete(mood: Mood)

    @Query("SELECT * from userMood WHERE id = :id")
    fun getItem(id: Int): Flow<Mood>

    @Query("SELECT * from userMood ORDER BY id ASC")
    fun getAllItems(): Flow<List<Mood>>
}