package com.noalecohen.dispatcher.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.CustomFilterBarBinding

class CustomFilterBar(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var view: View = inflate(context, R.layout.custom_filter_bar, this)

    private var binding: CustomFilterBarBinding

    init {
        binding = CustomFilterBarBinding.bind(view)
    }
}