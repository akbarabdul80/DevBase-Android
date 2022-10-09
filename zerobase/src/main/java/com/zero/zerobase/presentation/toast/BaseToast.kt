package com.zero.zerobase.presentation.toast

import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.zero.zerobase.utils.AppContext
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun showShortToast(message: String) {
    Toast.makeText(AppContext.get(), message, Toast.LENGTH_SHORT).show()
}

fun showLongToast(message: String) {
    Toast.makeText(AppContext.get(), message, Toast.LENGTH_LONG).show()
}

fun toastThrowable(error: Throwable) {
    when (error) {
        is HttpException -> {
            when (error.code()) {
                400 -> {
                    Toast.makeText(AppContext.get(), "Silahkan cek koneksi anda", Toast.LENGTH_LONG)
                        .show()
                }
                404 -> Toast.makeText(
                    AppContext.get(),
                    "Url yang diminta tidak ditemukan",
                    Toast.LENGTH_LONG
                ).show()

                500 -> Toast.makeText(AppContext.get(), "Server sedang gangguan", Toast.LENGTH_LONG)
                    .show()
            }
        }
        is SocketTimeoutException -> {
            Toast.makeText(AppContext.get(), "Silahkan cek koneksi anda", Toast.LENGTH_LONG).show()
        }
    }
}

fun View.showSnackBar(
    message: String,
    isItLong: Boolean = false,
    action: Pair<String, View.OnClickListener>? = null
) {
    Snackbar.make(this, message, if (isItLong) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT)
        .apply {
            action?.let { setAction(it.first, it.second) }
            show()
        }
}