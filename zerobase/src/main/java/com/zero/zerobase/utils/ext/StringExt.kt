package com.zero.zerobase.utils.ext

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Int.toRupiah(): String {
    return DecimalFormat("###,###,###,###").format(this)
}


fun Double.toRupiah(): String {
    return DecimalFormat("###,###,###,###.##").format(this)
}

fun String.dateFormat(): String {
    var format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val newDate: Date? = format.parse(this)
    format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return newDate?.let { format.format(it) } ?: orEmpty()
}

fun String.dateTimeFormat(): String {
    var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val newDate: Date? = format.parse(this)
    format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return newDate?.let { format.format(it) } ?: orEmpty()
}