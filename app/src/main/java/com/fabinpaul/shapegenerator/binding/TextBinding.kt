package com.fabinpaul.shapegenerator.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextBinding {

    @BindingAdapter("android:textSrc")
    @JvmStatic
    fun setPaddingLeft(textView: TextView, textSrc: Int) {
        if (textSrc > 0) {
            textView.text = textView.context.getString(textSrc)
        }
    }
}