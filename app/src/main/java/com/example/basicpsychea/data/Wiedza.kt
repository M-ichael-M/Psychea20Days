package com.example.basicpsychea.data

import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class WiedzaData(
    val id: Int,
    @StringRes val title: Int,
    @StringRes val definicja: Int,
    @StringRes val objawy: Int
)

val wiedza_list = listOf(
    WiedzaData(0, R.string.depresja, R.string.definicja_depresji, R.string.objawy_depresji),
    WiedzaData(1, R.string.zaburzenia_lękowe, R.string.definicja_zaburzeń_lękowych, R.string.przykłady_zaburzeń_lękowych),
    WiedzaData(2, R.string.zaburzenia_osobowości, R.string.definicja_zaburzeń_osobowości, R.string.przykłady_zaburzeń_osobowości),
    WiedzaData(3, R.string.zaburzenia_afektywne_dwubiegunowe, R.string.definicja_zaburzeń_afektywnych_dwubiegunowych, R.string.objawy_zaburzeń_afektywnych_dwubiegunowych),
    WiedzaData(4, R.string.zaburzenia_psychotyczne, R.string.definicja_zaburzeń_psychotycznych, R.string.przykłady_zaburzeń_psychotycznych),
    WiedzaData(5, R.string.zaburzenia_odżywiania, R.string.definicja_zaburzeń_odżywiania, R.string.przykłady_zaburzeń_odżywiania),
    WiedzaData(6, R.string.adhd, R.string.definicja_adhd, R.string.objawy_adhd),
    WiedzaData(7, R.string.zaburzenia_snu, R.string.definicja_zaburzeń_snu, R.string.przykłady_zaburzeń_snu),
    WiedzaData(8, R.string.zaburzenia_używania_substancji, R.string.definicja_zaburzeń_używania_substancji, R.string.przykłady_zaburzeń_używania_substancji),
    WiedzaData(9, R.string.zaburzenia_nerwicowe, R.string.definicja_zaburzeń_nerwicowych, R.string.przykłady_zaburzeń_nerwicowych)
)
