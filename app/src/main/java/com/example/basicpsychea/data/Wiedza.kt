package com.example.basicpsychea.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class WiedzaData(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val wiedza_list = listOf(
    WiedzaData(0, R.drawable.ikona, R.string.test_1, R.string.test_2)
)