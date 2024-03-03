package com.example.basicpsychea.data.mood

import kotlinx.coroutines.flow.Flow

interface MoodRepository {
    fun getAllItemsStream(): Flow<List<Mood>>

    fun getItemStream(id: Int): Flow<Mood?>

    suspend fun insertItem(item: Mood)

    suspend fun deleteItem(item: Mood)
    suspend fun updateItem(item: Mood)
}