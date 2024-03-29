package com.example.basicpsychea.data.mood

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basicpsychea.data.goals.Goal
import com.example.basicpsychea.data.goals.GoalDao

@Database(entities = [Mood::class, Goal::class], version = 1, exportSchema = false)
abstract class MoodDatabase : RoomDatabase(){
    abstract fun moodDao():
            MoodDao
    abstract fun goalDao():
            GoalDao
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