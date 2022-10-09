package com.zero.zerobase.utils.ext

import java.text.NumberFormat
import java.util.*

/**
 * method to convert string to currency
 * @author Jimly A.
 * @since 02-Nov-21
 * @param locale Local of the currency format, by default `Locale.getDefault()`
 * @return Formatted string based on Currency locale
 **/
fun String.toCurrency(locale: Locale = Locale.getDefault()): String {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    formatter.maximumFractionDigits = 2
    return formatter.format(this.toDouble())
}