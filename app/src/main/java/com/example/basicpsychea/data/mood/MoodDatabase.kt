package com.example.basicpsychea.data.mood

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mood::class], version = 1)
abstract class MoodDatabase : RoomDatabase(){
    abstract fun moodDao():
            MoodDao
}

object MoodDb
{
    private var db: MoodDatabase? = null

    fun getInstance(context: Context): MoodDatabase {
        if (db == null)
        {
            db = Room.databaseBuilder(context, MoodDatabase::class.java, "psychea-database").build()
        }
        return db!!
    }
}