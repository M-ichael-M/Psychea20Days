package com.example.basicpsychea.data.mood

import kotlinx.coroutines.flow.Flow

class OfflineMoodRepository(private val moodDao: MoodDao) : MoodRepository {
    override fun getAllItemsStream(): Flow<List<Mood>> = moodDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Mood?> = moodDao.getItem(id)

    override suspend fun insertItem(item: Mood) = moodDao.insert(item)

    override suspend fun deleteItem(item: Mood) = moodDao.delete(item)

    override suspend fun updateItem(item: Mood) = moodDao.update(item)
}