package com.example.wheel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin


class Wheel @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {
    private val STROKE_WIDTH = 20F
    private val greyRingPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = STROKE_WIDTH
        color = ContextCompat.getColor(getContext(), R.color.grey)
    }
    private val greenRingPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = STROKE_WIDTH
        color = ContextCompat.getColor(getContext(), R.color.green)
        strokeCap = Paint.Cap.ROUND
    }
    private val magentaCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(getContext(), R.color.magenta)
        style = Paint.Style.FILL
    }
    private var centerX = 0F
    private var centerY = 0F
    private var arcRadius = 0F
    private val blobRadius = 20F
    private var sweepAngle = 0F
    private val startAngle = -90F

    private val mRect: RectF by lazy {
        centerX = (measuredWidth / 2).toFloat()
        centerY = (measuredHeight / 2).toFloat()
        arcRadius = min(centerX, centerY)
        val startTop = (max(blobRadius, STROKE_WIDTH / 2)).toInt()
        val endBottom = 2 * arcRadius - startTop

        RectF(startTop.toFloat(), startTop.toFloat(), endBottom, endBottom)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        sweepAngle *= -1
        // draw grey ring
        canvas.drawArc(mRect, startAngle, 360F, false, greyRingPaint)
        // draw green arc
        canvas.drawArc(mRect, startAngle, sweepAngle, false, greenRingPaint)

        val sweepAngleInRadians = Math.toRadians(sweepAngle.toDouble())
        val sinSweepAngle = sin(sweepAngleInRadians)
        val cosSweepAngle = cos(sweepAngleInRadians)
        val cx = arcRadius * sinSweepAngle + arcRadius - blobRadius * sinSweepAngle
        val cy = -1 * arcRadius * cosSweepAngle + arcRadius + blobRadius * cosSweepAngle
        // draw blob
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), blobRadius, magentaCirclePaint)
    }

    fun resetSweepAngle() {
        sweepAngle = 0F
    }

    fun setSweepAngle(angle: Float) {
        sweepAngle = angle
    }
}