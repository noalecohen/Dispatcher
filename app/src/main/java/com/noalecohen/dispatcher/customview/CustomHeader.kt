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

    var view: View = inflate(context, R.layout.custom_header, this)

    var binding: CustomHeaderBinding

    var icon: ImageView
    var leftImageButton: ImageButton
    var rightImageButton: ImageButton


    init {
        binding = CustomHeaderBinding.bind(view)
        icon = binding.headerIcon
        leftImageButton = binding.headerLeftImageButton
        rightImageButton = binding.headerRightImageButton

        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomHeader, 0, 0)
            .apply {
                try {
                    icon.setImageDrawable(getDrawable(R.styleable.CustomHeader_firstIcon))
                    leftImageButton.background = getDrawable(R.styleable.CustomHeader_secondIcon)
                    rightImageButton.background = getDrawable(R.styleable.CustomHeader_thirdIcon)
                } catch (e: Exception) {
                    //TODO: handle exception
                } finally {
                    recycle()
                }
            }
    }

    fun isShowIcon(icon: ImageButton): Boolean {
        return icon.isVisible
    }

    fun setShowIcon(icon: View, toShow: Boolean) {
        icon.visibility = if (toShow) View.VISIBLE else View.GONE
        invalidate()
        requestLayout()
    }

    fun setIconClickListener(icon: ImageButton, callBack: OnClickListener) {
        icon.setOnClickListener(callBack)
    }
}