package com.noalecohen.dispatcher.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.CustomHeaderBinding

class CustomHeader(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var view: View = inflate(context, R.layout.custom_header, this)

    private var binding: CustomHeaderBinding

    init {
        binding = CustomHeaderBinding.bind(view)


        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomHeader, 0, 0)
            .apply {
                try {
                    if (getDrawable(R.styleable.CustomHeader_appLogo) == null) {
                        throw Exception(MISSING_APP_LOGO_EXCEPTION)
                    }

                    if (getDrawable(R.styleable.CustomHeader_icon1) == null) {
                        setVisibilityForIcon1(false)
                    }

                    if (getDrawable(R.styleable.CustomHeader_icon2) == null) {
                        setVisibilityForIcon2(false)
                    }

                    binding.customHeaderAppLogo.setImageDrawable(getDrawable(R.styleable.CustomHeader_appLogo))
                    binding.customHeaderIcon1.background =
                        getDrawable(R.styleable.CustomHeader_icon1)
                    binding.customHeaderIcon2.background =
                        getDrawable(R.styleable.CustomHeader_icon2)
                } catch (e: Exception) {
                    throw e
                } finally {
                    recycle()
                }
            }
    }

    fun isVisibleLogo(): Boolean {
        return binding.customHeaderAppLogo.isVisible
    }

    fun isVisibleIcon1(): Boolean {
        return binding.customHeaderIcon1.isVisible
    }

    fun isVisibleIcon2(): Boolean {
        return binding.customHeaderIcon2.isVisible
    }

    fun setVisibilityForLogo(toShow: Boolean) {
        binding.customHeaderAppLogo.visibility = if (toShow) View.VISIBLE else View.GONE
        invalidate()
        requestLayout()
    }

    fun setVisibilityForIcon1(toShow: Boolean) {
        binding.customHeaderIcon1.visibility = if (toShow) View.VISIBLE else View.GONE
        invalidate()
        requestLayout()
    }

    fun setVisibilityForIcon2(toShow: Boolean) {
        binding.customHeaderIcon2.visibility = if (toShow) View.VISIBLE else View.GONE
        invalidate()
        requestLayout()
    }

    fun setOnClickListenerIcon1(onClickListener: OnClickListener) {
        binding.customHeaderIcon2.setOnClickListener(onClickListener)
    }

    fun setOnClickListenerIcon2(onClickListener: OnClickListener) {
        binding.customHeaderIcon2.setOnClickListener(onClickListener)
    }

    fun setLogoDrawable(id: Int) {
        binding.customHeaderAppLogo.setImageDrawable(getDrawable(context, id))
    }

    fun setIcon1Drawable(id: Int) {
        binding.customHeaderIcon1.background = getDrawable(context, id)
    }

    fun setIcon2Drawable(id: Int) {
        binding.customHeaderIcon2.background = getDrawable(context, id)
    }

    fun getLogo(): ImageView {
        return binding.customHeaderAppLogo
    }

    fun getIcon1(): ImageButton {
        return binding.customHeaderIcon1
    }

    fun getIcon2(): ImageButton {
        return binding.customHeaderIcon2
    }


    companion object {
        const val MISSING_APP_LOGO_EXCEPTION = "AppLogo is mandatory attribute"
    }
}