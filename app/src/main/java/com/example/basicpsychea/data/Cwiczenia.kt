package com.example.basicpsychea.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class CwiczeniaData(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val cwiczenia_list = listOf(
    CwiczeniaData(0, R.drawable.ikona, R.string.tworcze_zajecia, R.string.tworcze_zajecia_opis),
    CwiczeniaData(1, R.drawable.ikona, R.string.czytanie, R.string.czytanie_opis),
    CwiczeniaData(2, R.drawable.ikona, R.string.kontakt_z_natura, R.string.kontakt_z_natura_opis),
    CwiczeniaData(3, R.drawable.ikona, R.string.medytacja, R.string.medytacja_opis),
    CwiczeniaData(4, R.drawable.ikona, R.string.aktywnosc_fizyczna, R.string.aktywnosc_fizyczna_opis),
    CwiczeniaData(5, R.drawable.ikona, R.string.prowadzenie_dziennika, R.string.prowadzenie_dziennika_opis),
    CwiczeniaData(6, R.drawable.ikona, R.string.dbanie_o_odpoczynek_i_sen, R.string.dbanie_o_odpoczynek_i_sen_opis),
    CwiczeniaData(7, R.drawable.ikona, R.string.wsparcie_spoleczne, R.string.wsparcie_spoleczne_opis),
    CwiczeniaData(8, R.drawable.ikona, R.string.zdrowa_dieta, R.string.zdrowa_dieta_opis),
    CwiczeniaData(9, R.drawable.ikona, R.string.cwiczenia_relaksacyjne, R.string.cwiczenia_relaksacyjne_opis),
    CwiczeniaData(10, R.drawable.ikona, R.string.rownowaga_pracy_i_odpoczynku, R.string.rownowaga_pracy_i_odpoczynku_opis),
    CwiczeniaData(11, R.drawable.ikona, R.string.pozytywne_afirmacje, R.string.pozytywne_afirmacje_opis)

)