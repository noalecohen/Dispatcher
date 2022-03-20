package com.noalecohen.dispatcher

import android.view.View
import android.widget.Toast
import model.BaseFragment

fun BaseFragment.displayToast(str: String) = Toast.makeText(context, str, Toast.LENGTH_LONG).show()

fun View.showView(boolean: Boolean): View {
    visibility = if (boolean) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
    return this
}