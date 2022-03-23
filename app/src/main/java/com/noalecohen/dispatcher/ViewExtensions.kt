package com.noalecohen.dispatcher

import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.noalecohen.dispatcher.model.BaseFragment

fun BaseFragment.displayToast(str: String) = Toast.makeText(context, str, Toast.LENGTH_LONG).show()

fun View.showView(boolean: Boolean): View {
    visibility = if (boolean) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
    return this
}

fun EditText.isValidInput(): Boolean {
    return this.text.filterNot { it.isWhitespace() }.isNotEmpty()
}