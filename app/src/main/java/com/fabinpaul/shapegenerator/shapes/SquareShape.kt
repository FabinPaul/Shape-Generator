package com.fabinpaul.shapegenerator.shapes

import android.graphics.drawable.GradientDrawable

class SquareShape(
    private val edge: Int,
    colorBg: Int,
    colorStroke: Int,
    width: Int,
    height: Int
) : GradientDrawable() {

    init {
        shape = RECTANGLE
        setColor(colorBg)
        setStroke(STROKE_WIDTH, colorStroke)
        setSize(width, height)
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        val width = right - left
        val height = bottom - top
        val centerX = width / 2 + left
        val centerY = height / 2 + top
        super.setBounds(
            centerX - edge / 2,
            centerY - edge / 2,
            centerX + edge / 2,
            centerY + edge / 2
        )
    }

    companion object {
        private const val STROKE_WIDTH = 6
    }
}