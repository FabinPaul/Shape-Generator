package com.fabinpaul.shapegenerator.shapes

import android.graphics.drawable.GradientDrawable

class CircleShape(
    private val radius: Int,
    colorBg: Int,
    colorStroke: Int
) : GradientDrawable() {

    init {
        shape = OVAL
        cornerRadii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        setColor(colorBg)
        setStroke(STROKE_WIDTH, colorStroke)
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        val width = right - left
        val height = bottom - top
        val centerX = width / 2 + left
        val centerY = height / 2 + top
        super.setBounds(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
    }

    companion object {
        private const val STROKE_WIDTH = 6
    }
}