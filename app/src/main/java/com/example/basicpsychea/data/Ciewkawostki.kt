package com.example.basicpsychea.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class CiewkawostkiData(
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val ciekawostki_list = listOf(
    CiewkawostkiData(R.drawable.ikona, R.string.test_1, R.string.test_2)
)