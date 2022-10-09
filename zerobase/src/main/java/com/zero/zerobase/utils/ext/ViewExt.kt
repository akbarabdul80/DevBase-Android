package com.zero.zerobase.utils.ext

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

/**
 * @author Jimly A.
 * @since 31-May-20.
 */

/**
 * Method to display View
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Method to hide view
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Method to remove view
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Method to enable view
 */
fun View.enable() {
    isEnabled = true
}

/**
 * Method to disable view
 */
fun View.disable() {
    isEnabled = false
}

/**
 * Method to define action when text changed on EditText
 * @param doWhenChange action to do when the text changed
 */
fun EditText.onTextChanged(doWhenChange: (text: String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            doWhenChange(p0.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // Intended to be empty
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // Intended to be empty
        }
    })
}