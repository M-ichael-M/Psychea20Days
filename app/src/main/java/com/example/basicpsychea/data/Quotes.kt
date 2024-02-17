package com.example.basicpsychea.data

import androidx.annotation.StringRes
import com.example.basicpsychea.R

data class QuotesData(
    val id: Int,
    @StringRes val quote: Int
)

val quotes_list = listOf(
    QuotesData(1, R.string.quote_1),
    QuotesData(2, R.string.quote_2),
    QuotesData(3, R.string.quote_3),
    QuotesData(4, R.string.quote_4),
    QuotesData(5, R.string.quote_5),
    QuotesData(6, R.string.quote_6),
    QuotesData(7, R.string.quote_7),
    QuotesData(8, R.string.quote_8),
    QuotesData(9, R.string.quote_9),
    QuotesData(10, R.string.quote_10),
    QuotesData(11, R.string.quote_11),
    QuotesData(12, R.string.quote_12),
    QuotesData(13, R.string.quote_13),
    QuotesData(14, R.string.quote_14),
    QuotesData(15, R.string.quote_15),
    QuotesData(16, R.string.quote_16),
    QuotesData(17, R.string.quote_17),
    QuotesData(18, R.string.quote_18),
    QuotesData(19, R.string.quote_19),
    QuotesData(20, R.string.quote_20)
)

