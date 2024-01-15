package com.example.basicpsychea.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class CwiczeniaData(
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val cwiczenia_list = listOf(
    CwiczeniaData(R.drawable.ikona, R.string.test_1, R.string.test_2)
)