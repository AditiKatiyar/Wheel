package com.example.wheel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wheel = findViewById<Wheel>(R.id.wheel_view)
        val applyButton = findViewById<Button>(R.id.apply_button)
        val angleEditText = findViewById<EditText>(R.id.angle_edit_text)

        applyButton.setOnClickListener {
            val angle = angleEditText.text.toString()
            if (angle.isNotEmpty()) {
                val animation = WheelAnimation(wheel, angle.toFloat()).apply {
                    duration = 3000
                }
                wheel.startAnimation(animation)
            } else {
                Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            }
        }
    }
}