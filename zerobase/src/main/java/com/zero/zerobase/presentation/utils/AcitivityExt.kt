package com.zero.zerobase.presentation.utils

import android.app.Activity

fun Activity.toast(message: String, duration: Int = android.widget.Toast.LENGTH_SHORT) {
    android.widget.Toast.makeText(this, message, duration).show()
}