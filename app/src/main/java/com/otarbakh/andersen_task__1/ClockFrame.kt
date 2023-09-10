package com.otarbakh.andersen_task__1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


class ClockFrame (
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val dashPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var radius: Float = 0f
    private var centerX: Float = 0f
    private var centerY: Float = 0f

    init {
        init()
    }

    private fun init() {
        dashPaint.color = Color.BLACK
        dashPaint.strokeWidth = 10f
        dashPaint.pathEffect = DashPathEffect(floatArrayOf(20f, 0f), 0f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = w / 2f
        centerY = h / 2f
        radius = min(centerX, centerY) - dashPaint.strokeWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until 12) {
            val angle = Math.toRadians(i * 30.0)
            val startX = (centerX + radius * sin(angle)).toFloat()
            val startY = (centerY - radius * cos(angle)).toFloat()
            val stopX = (centerX + (radius - 50f) * sin(angle)).toFloat()
            val stopY = (centerY - (radius - 50f) * cos(angle)).toFloat()
            canvas.drawLine(startX, startY, stopX, stopY, dashPaint)
        }
    }
}