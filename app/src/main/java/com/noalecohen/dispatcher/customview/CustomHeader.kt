package com.noalecohen.dispatcher.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.CustomHeaderBinding

class CustomHeader(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    var binding: CustomHeaderBinding
    var firstIcon: ImageView
    var secondIcon: ImageButton
    var thirdIcon: ImageButton
    var view: View = inflate(context, R.layout.custom_header, this)

    init {
        binding = CustomHeaderBinding.bind(view)
        firstIcon = binding.headerFirstIcon
        secondIcon = binding.headerSecondIcon
        thirdIcon = binding.headerThirdIcon
    }

    fun isShowIcon(icon: ImageButton): Boolean {
        return icon.isVisible
    }

    fun setShowIcon(icon: View, toShow: Boolean) {
        icon.visibility = if(toShow) View.VISIBLE else View.GONE
    }

    fun setIconClickListener(icon: ImageButton, callBack: OnClickListener) {
        icon.setOnClickListener(callBack)
    }

    fun setIconDrawable(icon: ImageButton, drawableId: Int) {
        icon.setBackgroundResource(drawableId)
    }
}