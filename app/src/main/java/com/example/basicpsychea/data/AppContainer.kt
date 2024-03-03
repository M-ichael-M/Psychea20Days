package com.example.basicpsychea.data

import android.content.Context
import com.example.basicpsychea.data.mood.MoodDatabase
import com.example.basicpsychea.data.mood.MoodRepository
import com.example.basicpsychea.data.mood.OfflineMoodRepository

interface AppContainer {
    val moodRepository: MoodRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val moodRepository: MoodRepository by lazy {
        OfflineMoodRepository(MoodDatabase.getDatabase(context).moodDao())
    }
}