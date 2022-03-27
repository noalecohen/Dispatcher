package com.noalecohen.dispatcher.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.noalecohen.dispatcher.R

class CustomHeader(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_header, this, false)
        addView(view)
    }
}