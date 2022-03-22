package com.noalecohen.dispatcher

import android.widget.ArrayAdapter

fun <T> ArrayAdapter<T>.update(list: List<T>) {
    this.clear()
    this.addAll(list)
    this.notifyDataSetChanged()
}