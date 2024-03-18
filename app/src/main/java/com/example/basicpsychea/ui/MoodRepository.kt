package com.example.basicpsychea.ui

import android.content.Context
import com.example.basicpsychea.data.mood.Mood
import com.example.basicpsychea.data.mood.MoodDao
import com.example.basicpsychea.data.mood.MoodDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MoodRepository(context: Context): MoodDao {
    private val dao = MoodDb.getInstance(context).moodDao()
    override suspend fun insertAll(moods: List<Mood>) = withContext(Dispatchers.IO){
        dao.insertAll(moods)
    }

    override suspend fun delete(moods: List<Mood>) = withContext(Dispatchers.IO){
        dao.delete(moods)
    }

    override suspend fun update(mood: Mood) = withContext(Dispatchers.IO){
        dao.update(mood)
    }

    override fun getAll(): Flow<List<Mood>> {
        return dao.getAll()
    }

    override suspend fun dropDatabase() = withContext(Dispatchers.IO){
        dao.dropDatabase()
    }

    override fun getLast(): Flow<Long> {
        return dao.getLast()
    }

    override suspend fun insertOne(mood: Mood) {
        dao.insertOne(mood)
    }

    override fun howMany(): Flow<Int> {
        return dao.howMany()
    }


}