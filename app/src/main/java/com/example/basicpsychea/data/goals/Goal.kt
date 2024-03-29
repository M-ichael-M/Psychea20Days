package com.example.basicpsychea.data.goals

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

object ToolsConverter {
    @TypeConverter
    fun fromList(list: List<Int>?): String? {
        return list?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toList(data: String?): List<Int>? {
        return data?.split(",")?.map { it.toInt() }
    }
}
object userTextConverter
{
    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return list?.joinToString(separator = ",")
    }
    @TypeConverter
    fun toList(data: String?): List<String>? {
        return data?.split(",")?.map { it }
    }
}

@Entity(tableName = "user_goals")
@TypeConverters(ToolsConverter::class, userTextConverter::class)
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val toDo: Int = 5,
    val tools: List<Int>, //Medytacje, spotify, clearworries, waves
    val userText: List<String>,
    val daysSinceInstallation: Int,
)