package com.example.basicpsychea.data

import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class infoliniaData(
    @StringRes val title: Int, val phoneNumber: Int)

val infolinia_list = listOf(
    infoliniaData(R.string.telefon_zaufania_dla_dzieci_i_m_odzie_y, R.string._116111),
    infoliniaData(R.string.ca_odobowa_linia_wsparcia, R.string._800702222)

)