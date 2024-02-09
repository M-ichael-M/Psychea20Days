package com.example.basicpsychea.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class NawykiData(
    val id: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val nawyki_list = listOf(
    NawykiData(0, R.string.regularna_aktywnosc_fizyczna_title, R.string.regularna_aktywnosc_fizyczna_description),
    NawykiData(1, R.string.zrownowazona_dieta_title, R.string.zrownowazona_dieta_description),
    NawykiData(2, R.string.wystarczajacy_sen_title, R.string.wystarczajacy_sen_description),
    NawykiData(3, R.string.zarzadzanie_stresem_title, R.string.zarzadzanie_stresem_description),
    NawykiData(4, R.string.spoleczne_wsparcie_title, R.string.spoleczne_wsparcie_description),
    NawykiData(5, R.string.rozwoj_pasji_title, R.string.rozwoj_pasji_description),
    NawykiData(6, R.string.utrzymanie_rownowagi_zycia_title, R.string.utrzymanie_rownowagi_zycia_description)
)
