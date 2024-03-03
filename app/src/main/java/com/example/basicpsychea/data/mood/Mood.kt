package com.example.basicpsychea.data.mood

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userMood")
data class Mood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val emotion: Int,
    val dateOfEmotion: Double,
)
