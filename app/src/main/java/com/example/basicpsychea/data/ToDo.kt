package com.example.basicpsychea.data

import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class ToDoData(
    val id: Int,
    @StringRes val todo1: Int,
    @StringRes val todo2: Int
)

val todoList = listOf(
    ToDoData(0, R.string.todo1_1, R.string.todo1_2),
    ToDoData(1, R.string.todo2_1, R.string.todo2_2),
    ToDoData(2, R.string.todo3_1, R.string.todo3_2),
    ToDoData(3, R.string.todo4_1, R.string.todo4_2),
    ToDoData(4, R.string.todo5_1, R.string.todo5_2),
    ToDoData(5, R.string.todo6_1, R.string.todo6_2),
    ToDoData(6, R.string.todo7_1, R.string.todo7_2),
    ToDoData(7, R.string.todo8_1, R.string.todo8_2),
    ToDoData(8, R.string.todo9_1, R.string.todo9_2),
    ToDoData(9, R.string.todo10_1, R.string.todo10_2),
    ToDoData(10, R.string.todo11_1, R.string.todo11_2),
    ToDoData(11, R.string.todo12_1, R.string.todo12_2),
    ToDoData(12, R.string.todo13_1, R.string.todo13_2),
    ToDoData(13, R.string.todo14_1, R.string.todo14_2),
    ToDoData(14, R.string.todo15_1, R.string.todo15_2),
    ToDoData(15, R.string.todo16_1, R.string.todo16_2),
    ToDoData(16, R.string.todo17_1, R.string.todo17_2),
    ToDoData(17, R.string.todo18_1, R.string.todo18_2),
    ToDoData(18, R.string.todo19_1, R.string.todo19_2),
    ToDoData(19, R.string.todo20_1, R.string.todo20_2)
)