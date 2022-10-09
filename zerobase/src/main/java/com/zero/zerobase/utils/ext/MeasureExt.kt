package com.zero.zerobase.utils.ext

import android.content.res.Resources

/**
 * @author Jimly A.
 * @since 31-May-20.
 */

/**
 * Method to convert int to Dp
 */
val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

/**
 * Method to convert int to Px
 */
val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()