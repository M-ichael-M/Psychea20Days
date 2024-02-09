package com.example.basicpsychea.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class CiewkawostkiData(
    val id: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val ciekawostki_list = listOf(
    CiewkawostkiData(0, R.string.regularna_aktywnosc_fizyczna_title, R.string.regularna_aktywnosc_fizyczna_description),
)
