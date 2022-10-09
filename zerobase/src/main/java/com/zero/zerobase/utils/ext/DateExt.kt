package com.zero.zerobase.utils.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Jimly A.
 * @since 17-Jun-20.
 */

/**
 * Extension method from [String] to parse to [Date]
 * @param fromFormat format date from [String]
 * @param locale the locale of the string, default value = Locale.getDefault()
 * @return [Date] if parsing success and null if there's exception
 */
fun String.toDate(fromFormat: String, locale: Locale = Locale.getDefault()): Date? {
    return try {
        SimpleDateFormat(fromFormat, locale).parse(this)
    } catch (t: Throwable) {
        null
    }
}

/**
 * Extension method from date to format [Date] to [String]
 * @param toFormat format [String] to be formatted
 * @param locale the locale of the result string, default value = Locale.getDefault()
 * @return [String] if formatting success and null if there's exception
 */
fun Date.toString(toFormat: String, locale: Locale = Locale.getDefault()): String? {
    return try {
        SimpleDateFormat(toFormat, locale).format(this)
    } catch (t: Throwable) {
        null
    }
}

/**
 * Extension method from [Long] to format date to [String]
 * @param toFormat format [String] to be formatted
 * @param locale the locale of the result string, default value = Locale.getDefault()
 * @return [String] if formatting success and null if there's exception
 */
fun Long.toDate(toFormat: String, locale: Locale = Locale.getDefault()) = try {
    SimpleDateFormat(toFormat, locale).format(Date(this))
} catch (t: Throwable) {
    null
}