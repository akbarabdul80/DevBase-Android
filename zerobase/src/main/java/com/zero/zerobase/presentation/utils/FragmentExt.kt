package com.zero.zerobase.presentation.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String, duration: Int = android.widget.Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}