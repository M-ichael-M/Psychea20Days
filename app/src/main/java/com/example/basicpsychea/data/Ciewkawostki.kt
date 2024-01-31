package com.example.basicpsychea.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class CiewkawostkiData(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val ciekawostki_list = listOf(
    CiewkawostkiData(0, R.drawable.ikona, R.string.test_1, R.string.test_2)
)