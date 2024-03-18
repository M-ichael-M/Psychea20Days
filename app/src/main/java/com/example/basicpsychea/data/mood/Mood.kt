package com.example.basicpsychea.data.mood

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_mood")
data class Mood(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val emotion: Int,
    val dateOfEmotion: Long,
)