package com.junka.myhero.common

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String?.toDateFormat(): String {
    this?.let {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = DateFormat.getDateInstance(DateFormat.LONG)
        val date = inputFormat.parse(this)
        date?.let {
            return outputFormat.format(date)
        } ?: return ""
    } ?: return ""
}