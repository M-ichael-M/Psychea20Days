package com.example.basicpsychea.ui

import android.content.Context
import com.example.basicpsychea.data.mood2.Mood
import com.example.basicpsychea.data.mood2.MoodDao
import com.example.basicpsychea.data.mood2.MoodDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MoodRepository(context: Context):MoodDao {
    private val dao = MoodDb.getInstance(context).moodDao()
    override suspend fun insertAll(moods: List<Mood>) = withContext(Dispatchers.IO){
        dao.insertAll(moods)
    }

    override suspend fun delete(moods: List<Mood>) = withContext(Dispatchers.IO){
        dao.delete(moods)
    }

    override suspend fun update(mood: Mood) = withContext(Dispatchers.IO) {
        dao.update(mood)
    }

    override fun getAll(): Flow<List<Mood>> {
        return dao.getAll()
    }

    override suspend fun dropDatabase() = withContext(Dispatchers.IO){
        dao.dropDatabase()
    }

    override fun getLast(): Flow<Double> {
       return dao.getLast()
    }


}