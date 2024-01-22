package com.example.basicpsychea.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class myData(
    @StringRes val title: Int, )

val my_list = listOf(
    myData(R.string.micha_ma_eczek),
    myData(R.string.krzysztof_rataj),
    myData(R.string.jacek_rogalski),
    myData(R.string.szymon_piekutowski),
    myData(R.string.mateusz_daczkowski)
)