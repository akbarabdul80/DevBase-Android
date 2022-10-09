package com.zero.zerobase.utils.ext

/**
 * @author Jimly A.
 * @since 31-May-20.
 */

/**
 * Method to check if Any's value is null
 * @return is Any null?
 */
fun Any?.isNull(): Boolean {
    return this == null
}

/**
 * Method to check if Any has a value
 * @return is Any not null?
 */
fun Any?.isNotNull(): Boolean {
    return this != null
}