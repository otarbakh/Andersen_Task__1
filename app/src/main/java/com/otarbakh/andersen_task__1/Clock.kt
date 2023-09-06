package com.otarbakh.andersen_task__1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.LongSparseArray
import android.view.View
import android.view.translation.ViewTranslationResponse

class Clock(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var handColor = Color.BLUE
    private var handWidth = 8.0f // Width of the clock hand
    private var handLength = 360 // Length of the clock hand
    private var rotationAngle = 180f // Initial rotation angle
    private var borderColor = Color.BLACK
    private var borderWidth = 4.0f
    private var size = 320

    private val rotationHandler = Handler(Looper.getMainLooper())

    init {
        // Start rotating the rectangle every second
        startRotation()
    }

    fun clockFrame(canvas: Canvas?){
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        val radius = size + 35

        // 5
        canvas!!.drawCircle(width / 2f, height / 2f, radius - borderWidth / 2f, paint)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.rotate(rotationAngle, width / 2f, height / 2f)
        drawSecondsClockHand(canvas)
        canvas?.restore()
        clockFrame(canvas)
    }

    private fun drawSecondsClockHand(canvas: Canvas?) {
        canvas?.apply {
            paint.color = handColor
            paint.style = Paint.Style.FILL

            val centerX = width / 2f
            val centerY = height / 2f

            // Calculate the coordinates for the two ends of the clock hand
            val startX = centerX - handWidth / 2
            val endX = centerX + handWidth / 2
            val startY = centerY - handLength / 200
            val endY = centerY + handLength / 1

            drawRect(startX, startY, endX, endY, paint)
        }
    }

    private fun drawMinutesClockHand(canvas: Canvas?) {
        canvas?.apply {
            paint.color = handColor
            paint.style = Paint.Style.FILL

            val centerX = width / 2f
            val centerY = height / 2f

            // Calculate the coordinates for the two ends of the clock hand
            val startX = centerX - handWidth / 2
            val endX = centerX + handWidth / 2
            val startY = centerY - handLength / 200
            val endY = centerY + handLength / 1

            drawRect(startX, startY, endX, endY, paint)
        }
    }


    private fun startRotation() {
        rotationHandler.postDelayed({
            // Rotate the rectangle by 6 degrees
            rotationAngle += 6f
            // Ensure the angle stays within 360 degrees
            if (rotationAngle >= 360f) {
                rotationAngle -= 360f
            }
            // Redraw the view
            invalidate()
            // Schedule the next rotation
            startRotation()
        }, 1000) // Rotate every 1000 milliseconds (1 second)
    }
}
