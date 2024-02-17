package com.example.basicpsychea.data

import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class CiekawostkiData(
    val id: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val ciekawostki_list = listOf(
    CiekawostkiData(1, R.string.nieswiadomosc_title, R.string.nieswiadomosc_description),
    CiekawostkiData(2, R.string.deja_vu_title, R.string.deja_vu_description),
    CiekawostkiData(3, R.string.sklonnosc_do_ulegania_wplywom_grupowym_title, R.string.sklonnosc_do_ulegania_wplywom_grupowym_description),
    CiekawostkiData(4, R.string.efekt_bumerangu_title, R.string.efekt_bumerangu_description),
    CiekawostkiData(5, R.string.plastycznosc_mozgu_title, R.string.plastycznosc_mozgu_description)
)
