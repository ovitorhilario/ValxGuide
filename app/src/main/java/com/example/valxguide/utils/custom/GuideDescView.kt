package com.example.valxguide.utils.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.valxguide.R
import com.example.valxguide.databinding.GuideDescViewBinding

class GuideDescView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding by lazy {
        GuideDescViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.GuideDescView,
            0, 0).apply {

            try {
                getString(R.styleable.GuideDescView_labelText)?.let { setLabelText(it) }
                getString(R.styleable.GuideDescView_contentText)?.let { setContentText(it) }
            } finally {
                recycle()
            }
        }
    }

    fun setLabelText(text: String) {
        binding.tvGuideDescLabel.text = text
    }

    fun setContentText(text: String) {
        binding.tvGuideDescContent.text = text
    }
}