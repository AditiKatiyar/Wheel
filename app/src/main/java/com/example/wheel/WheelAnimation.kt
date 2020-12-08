package com.example.wheel

import android.view.animation.Animation
import android.view.animation.Transformation

class WheelAnimation(private val wheel: Wheel, private val progress: Float) : Animation() {

    init {
        wheel.resetSweepAngle()
    }

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
        val angle: Float = progress * interpolatedTime
        wheel.setSweepAngle(angle)
        wheel.requestLayout()
    }
}