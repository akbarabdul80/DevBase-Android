package com.zero.zerobase.presentation.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.view.View
import android.widget.TextView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zero.zerobase.presentation.viewbinding.BaseActivity
import com.zero.zerobase.presentation.viewbinding.BaseBottomSheetFragment
import com.zero.zerobase.presentation.viewbinding.BaseFragment
import com.zero.zerobase.utils.FORMAT_DATE
import java.text.SimpleDateFormat
import java.util.*


fun View.animateClick() {
    val returned: () -> Unit = {
        animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(150)
            .setInterpolator(FastOutSlowInInterpolator())
            .start()
    }
    animate()
        .scaleX(0.95f)
        .scaleY(0.95f)
        .setDuration(150)
        .withEndAction(returned)
        .setInterpolator(FastOutSlowInInterpolator())
        .start()
}

fun <V : ViewBinding> BaseFragment<V>.showDialog(
    title: String,
    message: String,
    positiveAction: Pair<String, () -> Unit>,
    negativeAction: Pair<String, () -> Unit>,
    isCancelable: Boolean = true,
) {
    MaterialAlertDialogBuilder(requireActivity())
        .setTitle(title)
        .setMessage(message)
        .setCancelable(isCancelable)
        .setPositiveButton(positiveAction.first) { dialog, _ ->
            positiveAction.second.invoke()
            dialog.dismiss()
        }
        .setNegativeButton(negativeAction.first) { dialog, _ ->
            negativeAction.second.invoke()
            dialog.dismiss()
        }
        .show()
}

fun <V : ViewBinding> BaseActivity<V>.showDialog(
    title: String,
    message: String,
    positiveAction: Pair<String, () -> Unit>,
    negativeAction: Pair<String, () -> Unit>,
    isCancelable: Boolean = true,
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(isCancelable)
        .setPositiveButton(positiveAction.first) { dialog, _ ->
            positiveAction.second.invoke()
            dialog.dismiss()
        }
        .setNegativeButton(negativeAction.first) { dialog, _ ->
            negativeAction.second.invoke()
            dialog.dismiss()
        }
        .show()
}

@SuppressLint("SimpleDateFormat")
fun <V : ViewBinding> BaseActivity<V>.showDatePicker(
    view: TextView,
    calendar: Calendar? = Calendar.getInstance(),
    onFinish: () -> Unit = {}
) {
    DatePickerDialog(
        this,
        { _, year, monthOfYear, dayOfMonth ->
            calendar?.set(Calendar.YEAR, year)
            calendar?.set(Calendar.MONTH, monthOfYear)
            calendar?.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val sdf =
                SimpleDateFormat(FORMAT_DATE)
            view.text = calendar?.time?.let { sdf.format(it) }
            onFinish()
        },
        calendar!!.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}


@SuppressLint("SimpleDateFormat")
fun <V : ViewBinding> BaseBottomSheetFragment<V>.showDatePicker(
    view: TextView,
    calendar: Calendar? = Calendar.getInstance(),
    onFinish: () -> Unit = {}
) {
    DatePickerDialog(
        requireContext(),
        { _, year, monthOfYear, dayOfMonth ->
            calendar!!.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val sdf =
                SimpleDateFormat(FORMAT_DATE)
            view.text = sdf.format(calendar.time)
            onFinish()
        },
        calendar!!.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

@SuppressLint("SimpleDateFormat")
fun <V : ViewBinding> BaseFragment<V>.showDatePicker(
    view: TextView,
    calendar: Calendar,
    onFinish: () -> Unit
) {
    DatePickerDialog(
        requireContext(),
        { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val sdf =
                SimpleDateFormat(FORMAT_DATE)
            view.text = sdf.format(calendar.time)
            onFinish.invoke()
        },
        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

@SuppressLint("SimpleDateFormat")
fun <V : ViewBinding> BaseActivity<V>.showDateTimePicker(
    view: TextView,
    calendar: Calendar,
) {
    DatePickerDialog(
        this,
        { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val sdf =
                SimpleDateFormat(FORMAT_DATE)
            view.text = sdf.format(calendar.time)
        },
        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}