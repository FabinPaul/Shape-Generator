package com.fabinpaul.shapegenerator.shapes

import android.graphics.drawable.GradientDrawable

class Rectangle(
    private val length: Int,
    private val width: Int,
    colorBg: Int,
    colorStroke: Int,
    canvasWidth: Int,
    canvasHeight: Int
) : GradientDrawable() {

    init {
        shape = RECTANGLE
        setColor(colorBg)
        setStroke(STROKE_WIDTH, colorStroke)
        setSize(canvasWidth, canvasHeight)
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        val width = right - left
        val height = bottom - top
        val centerX = width / 2 + left
        val centerY = height / 2 + top
        super.setBounds(
            centerX - this.width / 2,
            centerY - length / 2,
            centerX + this.width / 2,
            centerY + length / 2
        )
    }

    companion object {
        private const val STROKE_WIDTH = 6
    }
}