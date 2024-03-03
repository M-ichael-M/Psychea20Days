package com.example.basicpsychea.data.mood

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mood::class], version = 1, exportSchema = false)
abstract class MoodDatabase : RoomDatabase(){
    abstract fun moodDao(): MoodDao

    companion object {
        @Volatile
        private var Instance: MoodDatabase? = null

        fun getDatabase(context: Context): MoodDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MoodDatabase::class.java, "psychea_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}