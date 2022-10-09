package com.zero.zerobase.utils.ext

import android.content.Context
import android.content.pm.ApplicationInfo

/**
 * To check does the current application is debuggable
 * @author Jimly A.
 * @since 14-Oct-21
 **/
val Context.isDebuggable: Boolean
    get() = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0