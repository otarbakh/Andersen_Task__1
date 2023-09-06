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
    private var rotationAngleSeconds = 230f // Initial rotation angle
    private var rotationAngleMinutes = 60f // Initial rotation angle
    private var rotationAngleHours = 110f // Initial rotation angle
    private var borderColor = Color.BLACK
    private var borderWidth = 10.0f
    private var size = 320

    private val rotationHandler = Handler(Looper.getMainLooper())

    init {
        // Start rotating the rectangle every second
        startSecondRotation()
        startMinuteRotation()
        startHourRotation()
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
        clockFrame(canvas)

        canvas?.save()
        canvas?.rotate(rotationAngleSeconds, width / 2f, height / 2f)
        drawSecondsClockHand(canvas)
        canvas?.restore()



        canvas?.save()
        canvas?.rotate(rotationAngleMinutes, width / 2f, height / 2f)
        drawMinutesClockHand(canvas)
        canvas?.restore()

        canvas?.save()
        canvas?.rotate(rotationAngleHours, width / 2f, height / 2f)
        drawHoursClockHand(canvas)
        canvas?.restore()
    }

    //Draw from here
    private fun drawSecondsClockHand(canvas: Canvas?) {
        canvas?.apply {
            paint.color = Color.RED
            paint.style = Paint.Style.FILL

            val centerX = width / 2f
            val centerY = height / 2f

            // Calculate the coordinates for the two ends of the clock hand
            val startX = centerX - handWidth / 2
            val endX = centerX + handWidth / 2
            val startY = centerY - handLength / 200
            val endY = centerY + handLength / 2

            drawRect(startX, startY, endX, endY, paint)
        }
    }

    private fun drawMinutesClockHand(canvas: Canvas?) {
        canvas?.apply {
            paint.color = Color.BLUE
            paint.style = Paint.Style.FILL

            val centerX = width / 2f
            val centerY = height / 2f

            // Calculate the coordinates for the two ends of the clock hand
            val startX = centerX - handWidth / 2
            val endX = centerX + handWidth / 2
            val startY = centerY - handLength / 200
            val endY = centerY + handLength / 1.5f

            drawRect(startX, startY, endX, endY, paint)
        }
    }

    private fun drawHoursClockHand(canvas: Canvas?) {
        canvas?.apply {
            paint.color = Color.BLACK
            paint.style = Paint.Style.FILL

            val centerX = width / 2f
            val centerY = height / 2f

            // Calculate the coordinates for the two ends of the clock hand
            val startX = centerX - handWidth / 0.8f
            val endX = centerX + handWidth / 2
            val startY = centerY - handLength / 200
            val endY = centerY + handLength / 2.5f

            drawRect(startX, startY, endX, endY, paint)
        }
    }



    //Rotations from here
    private fun startSecondRotation() {
        rotationHandler.postDelayed({
            // Rotate the rectangle by 6 degrees
            rotationAngleSeconds += 6f
            // Ensure the angle stays within 360 degrees
            if (rotationAngleSeconds >= 360f) {
                rotationAngleSeconds -= 360f
            }
            // Redraw the view
            invalidate()
            // Schedule the next rotation
            startSecondRotation()
        }, 61000) // Rotate every 1000 milliseconds (1 second)
    }

    private fun startMinuteRotation() {
        rotationHandler.postDelayed({
            // Rotate the rectangle by 6 degrees
            rotationAngleMinutes += 6f
            // Ensure the angle stays within 360 degrees
            if (rotationAngleMinutes >= 360f) {
                rotationAngleMinutes -= 360f
            }
            // Redraw the view
            invalidate()
            // Schedule the next rotation
            startMinuteRotation()
        }, 1000) // Rotate every 1000 milliseconds (1 second)
    }

    private fun startHourRotation() {
        rotationHandler.postDelayed({
            // Rotate the rectangle by 6 degrees
            rotationAngleHours += 30f
            // Ensure the angle stays within 360 degrees
            if (rotationAngleHours >= 360f) {
                rotationAngleHours -= 360f
            }
            // Redraw the view
            invalidate()
            // Schedule the next rotation
            startSecondRotation()
        }, 3600000) // Rotate every 1000 milliseconds (1 second)
    }
}
